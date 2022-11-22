import com.google.gson.Gson
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.junit.Test as Test

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertEquals(result.firstName, "Иван")
        assertEquals(result.lastName, "Иванов")
        assertEquals(result.phone, "89057125358")
        assertEquals(result.email, "ivanov@mail.ru")
        assertEquals(result.snils, "56553713311")
    }

    @Test
    fun `fail save client - validation error with phone`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(
            ErrorCode.INVALID_NUMBER
        )

        assertEquals(exceptionList.sorted(), exception.errorCode.sorted())
    }

    @Test
    fun `fail save client - validation error with name`() {
        val client = getClientFromJson("/fail/user_with_bad_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(
            ErrorCode.INVALID_NAME
        )

        assertEquals(exceptionList.sorted(), exception.errorCode.sorted())
    }

    @Test
    fun `fail save client - validation error with email`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(
            ErrorCode.INVALID_EMAIL
        )

        assertEquals(exceptionList.sorted(), exception.errorCode.sorted())
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(
            ErrorCode.INVALID_NAME,
            ErrorCode.INVALID_NUMBER,
            ErrorCode.INVALID_LAST_NAME,
            ErrorCode.INVALID_EMAIL,
            ErrorCode.INVALID_SNILS
        )

        assertEquals(exceptionList.sorted(), exception.errorCode.sorted())
    }

    @Test
    fun `fail save client - validation errors null`() {
        val client = getClientFromJson("/fail/user_with_null.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(
            ErrorCode.INVALID_NAME,
            ErrorCode.INVALID_NUMBER,
            ErrorCode.INVALID_LAST_NAME,
            ErrorCode.INVALID_EMAIL,
            ErrorCode.INVALID_SNILS
        )

        assertEquals(exceptionList.sorted(), exception.errorCode.sorted())
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - invalid phone, email, snils`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_PHONE_NUMBER, exception.errorCode[0])
        assertEquals(ErrorCode.INVALID_EMAIL, exception.errorCode[1])
        assertEquals(ErrorCode.INVALID_SNILS, exception.errorCode[2])
    }

    @Test
    fun `fail save client - names are invalid`() {
        val client = getClientFromJson("/fail/user_data_names_are_invalid.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.LAST_NAME_INVALID_CHARACTER, exception.errorCode[0])
        assertEquals(ErrorCode.FIRST_NAME_INVALID_CHARACTER, exception.errorCode[1])
    }

    @Test
    fun `fail save client - names are null`() {
        val client = getClientFromJson("/fail/user_data_names_are_null.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.LAST_NAME_IS_EMPTY, exception.errorCode[0])
        assertEquals(ErrorCode.LAST_NAME_IS_EMPTY, exception.errorCode[0])
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
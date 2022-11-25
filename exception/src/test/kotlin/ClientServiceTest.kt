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
    fun `fail save client - incorrect FIO`() {
        val client = getClientFromJson("/fail/incorrect_fio_user_data.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INCORRECT_FIRST_NAME)
        assertEquals(exception.errorCode[1], ErrorCode.INCORRECT_LAST_NAME)
    }

    @Test
    fun `fail save client - incorrect phone`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INCORRECT_PHONE_NUMBER)
    }

    @Test
    fun `fail save client - incorrect email`() {
        val client = getClientFromJson("/fail/incorrect_email_user_data.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INCORRECT_EMAIL)
    }

    @Test
    fun `fail save client - incorrect SNILS`() {
        val client = getClientFromJson("/fail/incorrect_snils_user_data.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INCORRECT_SNILS)
    }

    @Test
    fun `fail - empty user data`() {
        val client = getClientFromJson("/fail/empty_user_data.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.VALUE_IS_EMPTY)
        assertEquals(exception.errorCode[1], ErrorCode.VALUE_IS_EMPTY)
        assertEquals(exception.errorCode[2], ErrorCode.VALUE_IS_EMPTY)
        assertEquals(exception.errorCode[3], ErrorCode.VALUE_IS_EMPTY)
        assertEquals(exception.errorCode[4], ErrorCode.VALUE_IS_EMPTY)
    }

    @Test
    fun `fail - null user data`() {
        val client = getClientFromJson("/fail/null_user_data.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.VALUE_IS_EMPTY)
        assertEquals(exception.errorCode[1], ErrorCode.VALUE_IS_EMPTY)
        assertEquals(exception.errorCode[2], ErrorCode.VALUE_IS_EMPTY)
        assertEquals(exception.errorCode[3], ErrorCode.VALUE_IS_EMPTY)
        assertEquals(exception.errorCode[4], ErrorCode.VALUE_IS_EMPTY)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
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
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertThrows<ValidationException>("Недопустимый номер телефона") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE_NUMBER)
    }

    @Test
    fun `fail save client - name validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый символ в имени клиента") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_NAME)
    }

    @Test
    fun `fail save client - surname validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый символ в фамилии клиента") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_SURNAME)
    }

    @Test
    fun `fail save client - email validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый e-mail") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_EMAIL)
    }

    @Test
    fun `fail save client - snils validation errors`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Недопустимый номер СНИЛС") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS_NUMBER)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
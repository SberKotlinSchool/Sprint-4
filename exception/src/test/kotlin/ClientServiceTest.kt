import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun testSuccess() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun testBabPhone() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_PHONE_NUMBER, exception.errorCode[0])
    }

    @Test
    fun testBabEmail() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_EMAIL, exception.errorCode[0])
    }

    @Test
    fun testBabSnils() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_SNILS_CHECK_SUM, exception.errorCode[0])
    }

    @Test
    fun testBabSurname() {
        val client = getClientFromJson("/fail/user_with_bad_surname.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_NAME, exception.errorCode[0])
    }

    @Test
    fun testAllDataIncorrect() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
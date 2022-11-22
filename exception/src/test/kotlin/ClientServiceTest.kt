import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

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
    fun `fail save client - validation error throw ValidationException`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        assertThrows<ValidationException>("Выбрасывается исключение") {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - all data not valid`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(7, exception.errorCode.size)
    }

    @Test
    fun `fail save client - phone validation errors`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Невалидный номер телефона") {
            clientService.saveClient(client)
        }
        assertEquals(2, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_LENGTH))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_FIRST_DIGIT))
    }

    @Test
    fun `fail save client - snils checksum not valid`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Невалидный СНИЛС") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_SNILS_CHECKSUM))
    }

    @Test
    fun `fail save client - email not valid`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Невалидный email") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_EMAIL))
    }

    @Test
    fun `fail save client - firstname not valid`() {
        val client = getClientFromJson("/fail/user_with_bad_firstname.json")
        val exception = assertFailsWith<ValidationException>("Невалидное имя") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CYRILLIC))
    }

    @Test
    fun `fail save client - lastname not valid`() {
        val client = getClientFromJson("/fail/user_with_bad_lastname.json")
        val exception = assertFailsWith<ValidationException>("Невалидная фамилия") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CYRILLIC))
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
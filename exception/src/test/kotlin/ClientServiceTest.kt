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
    fun `fail save client - validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
    }

    // Данный тест закрывает 6 возможных ошибок. Позволю себе не делать 6 отдельных тестов на каждый вид
    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(6, exception.errorCode.size)
        assertEquals(ErrorCode.NULL_VALUE, exception.errorCode[0])
        assertEquals(ErrorCode.TOO_MUCH_LONG_STRING, exception.errorCode[1])
        assertEquals(ErrorCode.INVALID_VALUE_LENGTH, exception.errorCode[2])
        assertEquals(ErrorCode.INVALID_PHONE_CODE, exception.errorCode[3])
        assertEquals(ErrorCode.INCORRECT_EMAIL_FORMAT, exception.errorCode[4])
        assertEquals(ErrorCode.INVALID_SNILS_CONTROL_NUMBER, exception.errorCode[5])
    }

    @Test
    fun `fail save client with invalid snils control number`() {
        val client = getClientFromJson("/fail/user_invalid_snils_control_number.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_SNILS_CONTROL_NUMBER, exception.errorCode.first())
    }

    @Test
    fun `fail save client with invalid character`() {
        val client = getClientFromJson("/fail/user_invalid_character.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode.first())
    }

    @Test
    fun `fail save client with empty phone`() {
        val client = getClientFromJson("/fail/user_with_empty_phone.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.EMPTY_VALUE, exception.errorCode.first())
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
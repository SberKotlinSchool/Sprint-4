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
        val exc = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(exc.errorCode[0], ErrorCode.INCORRECT_STRING_LENGTH)
        assertEquals(exc.errorCode[1], ErrorCode.INVALID_CHARACTER)
        assertEquals(exc.errorCode.size, 2)
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INCORRECT_STRING_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_CHARACTER)
        assertEquals(exception.errorCode[2], ErrorCode.NULL_VALUE)
        assertEquals(exception.errorCode.size, 3)
    }

    @Test
    fun `fail save client - validation errors in first name`() {
        val client = getClientFromJson("/fail/user_data_corrupted_2.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.VALUE_TOO_BIG)
        assertEquals(exception.errorCode.size, 1)
    }

    @Test
    fun `fail save client - validation errors in all attributes`() {
        val client = getClientFromJson("/fail/user_data_corrupted_3.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INCORRECT_STRING_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_CHARACTER)
        assertEquals(exception.errorCode[2], ErrorCode.VALUE_TOO_BIG)
        assertEquals(exception.errorCode[3], ErrorCode.CYRILLIC_ISSUE)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_EMAIL)
        assertEquals(exception.errorCode[5], ErrorCode.INVALID_SNILS)

        assertEquals(exception.errorCode.size, 6)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
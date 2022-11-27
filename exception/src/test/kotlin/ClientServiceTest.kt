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
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertTrue { ErrorCode.INVALID_PHONE_FORMAT in exception.errorCode }
    }

    @Test
    fun `fail save client - all fields is corrupted`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
        assertTrue { ErrorCode.INVALID_FIRST_NAME_FORMAT in exception.errorCode }
        assertTrue { ErrorCode.INVALID_LAST_NAME_FORMAT in exception.errorCode }
        assertTrue { ErrorCode.INVALID_PHONE_FORMAT in exception.errorCode }
        assertTrue { ErrorCode.INVALID_SNILS_FORMAT in exception.errorCode }
        assertTrue { ErrorCode.INVALID_EMAIL_FORMAT in exception.errorCode }
    }

    @Test
    fun `fail save client - snils with invalid checksum`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_checksum.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertTrue { ErrorCode.INVALID_SNILS_CHECKSUM in exception.errorCode }
    }

    @Test
    fun `fail save client - all fields is missing`() {
        val client = getClientFromJson("/fail/user_with_empty_fields.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
        assertTrue { ErrorCode.MISSING_FIRST_NAME in exception.errorCode }
        assertTrue { ErrorCode.MISSING_LAST_NAME in exception.errorCode }
        assertTrue { ErrorCode.MISSING_PHONE in exception.errorCode }
        assertTrue { ErrorCode.MISSING_EMAIL in exception.errorCode }
        assertTrue { ErrorCode.MISSING_SNILS in exception.errorCode }
    }

    @Test
    fun `fail save client - first-last names validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(2, exception.errorCode.size)
        assertTrue { ErrorCode.INVALID_FIRST_NAME_FORMAT in exception.errorCode }
        assertTrue { ErrorCode.INVALID_LAST_NAME_FORMAT in exception.errorCode }
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")
}
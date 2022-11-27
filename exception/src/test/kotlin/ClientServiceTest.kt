import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = assertDoesNotThrow { clientService.saveClient(client) }
        assertEquals(2, result.version)
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        exception.errorCode.run {
            assertEquals(2, size)
            assertEquals(ErrorCode.PHONE_INVALID_LENGTH, get(0))
            assertEquals(ErrorCode.PHONE_STARTS_WITH_INVALID_CHARACTER, get(1))
        }
    }

    @Test
    fun `fail save client - snils checksum validation error`() {
        val client = getClientFromJson("/fail/user_bad_snils_checksum.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        exception.errorCode.run {
            assertEquals(1, size)
            assertEquals(ErrorCode.SNILS_INVALID_CHECK_SUM, get(0))
        }
    }

    @Test
    fun `fail save client - email domain validation error`() {
        val client = getClientFromJson("/fail/user_bad_email_domain.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        exception.errorCode.run {
            assertEquals(1, size)
            assertEquals(ErrorCode.EMAIL_INVALID_DOMAIN, get(0))
        }
    }

    @Test
    fun `fail save client - data length validation error`() {
        val client = getClientFromJson("/fail/user_bad_data_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        exception.errorCode.run {
            assertEquals(4, size)
            assertEquals(ErrorCode.NAME_EXCEEDS_LENGTH, get(0))
            assertEquals(ErrorCode.NAME_EXCEEDS_LENGTH, get(1))
            assertEquals(ErrorCode.PHONE_INVALID_LENGTH, get(2))
            assertEquals(ErrorCode.EMAIL_EXCEEDS_LENGTH, get(3))
        }
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        exception.errorCode.run {
            assertEquals(6, size)
            assertEquals(ErrorCode.EMPTY, get(0))
            assertEquals(ErrorCode.NAME_EXCEEDS_LENGTH, get(1))
            assertEquals(ErrorCode.PHONE_INVALID_LENGTH, get(2))
            assertEquals(ErrorCode.PHONE_STARTS_WITH_INVALID_CHARACTER, get(3))
            assertEquals(ErrorCode.EMAIL_INVALID_DOMAIN, get(4))
            assertEquals(ErrorCode.SNILS_INVALID_CHECK_SUM, get(5))
        }
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")
}

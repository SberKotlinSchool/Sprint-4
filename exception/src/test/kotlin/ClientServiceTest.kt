import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
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
    fun `fail save client - all fields validation error`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertThrows<ValidationException>() {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
        assertTrue { ErrorCode.INVALID_FIRST_NAME in exception.errorCode }
        assertTrue { ErrorCode.INVALID_LAST_NAME in exception.errorCode }
        assertTrue { ErrorCode.INVALID_PHONE_NUMBER in exception.errorCode }
        assertTrue { ErrorCode.INVALID_EMAIL in exception.errorCode }
        assertTrue { ErrorCode.INVALID_SNILS in exception.errorCode }
    }

    @Test
    fun `fail save client - all fields is null`() {
        val client = getClientFromJson("/fail/user_data_all_fields_is_null.json")
        val exception = assertThrows<ValidationException>() {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
        assertTrue { ErrorCode.NO_FIRST_NAME in exception.errorCode }
        assertTrue { ErrorCode.NO_LAST_NAME in exception.errorCode }
        assertTrue { ErrorCode.NO_PHONE_NUMBER in exception.errorCode }
        assertTrue { ErrorCode.NO_EMAIL in exception.errorCode }
        assertTrue { ErrorCode.NO_SNILS in exception.errorCode }
    }

    @Test
    fun `fail save client - snils digit validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_digit.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertTrue { ErrorCode.INVALID_SNILS_CHECK_DIGIT in exception.errorCode }
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
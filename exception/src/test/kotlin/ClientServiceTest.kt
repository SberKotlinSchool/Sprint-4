import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertDoesNotThrow { result }
    }

    @Test
    fun `fail save client - validation error firstName`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_FIRST_NAME)
    }

    @Test
    fun `fail save client - validation error lastName`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_LAST_NAME)
    }

    @Test
    fun `fail save client - validation error phone`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_PHONE)
    }

    @Test
    fun `fail save client - validation error email`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_EMAIL)
    }

    @Test
    fun `fail save client - validation error snils`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_SNILS)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")
}
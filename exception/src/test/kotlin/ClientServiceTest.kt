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

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_CHARACTER)
    }

    @Test
    fun `fail save client - all empty fields`() {
        val client = getClientFromJson("/fail/user_data_all_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_CHARACTER)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_CHARACTER)
        assertEquals(exception.errorCode[7], ErrorCode.INVALID_CHARACTER)
        assertEquals(exception.errorCode[10], ErrorCode.INVALID_CHARACTER)
    }

    @Test
    fun `fail save client - bad snils`() {
        val client = getClientFromJson("/fail/user_data_corrupted_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_CHARACTER)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_SNILS_CONTROL_SUM)
    }

    @Test
    fun `fail save client - bad email length`() {
        val client = getClientFromJson("/fail/user_data_corrupted_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_LENGTH)
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
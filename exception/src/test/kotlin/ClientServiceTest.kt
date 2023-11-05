import com.google.gson.Gson
import org.junit.Test
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
    fun `fail save client - validation error phone number`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_LENGTH, exception.errorCode[0])
        assertEquals(ErrorCode.INVALID_FIRST_DIGIT, exception.errorCode[1])
    }

    @Test
    fun `fail save client - validation error email`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_EMAIL, exception.errorCode[0])
    }

    @Test
    fun `fail save client - validation error name`() {
        val client = getClientFromJson("/fail/user_with_bad_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(3, exception.errorCode.size) // invalid name, invalid surname, invalid surname length
    }

    @Test
    fun `fail save client - validation error snils`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_SNILS_CHECKSUM, exception.errorCode[0])
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(6, exception.errorCode.size)
        assertEquals(ErrorCode.INVALID_LENGTH, exception.errorCode[0])
        assertEquals(ErrorCode.INVALID_FIRST_DIGIT, exception.errorCode[1])
        assertEquals(ErrorCode.INVALID_EMAIL, exception.errorCode[2])
        assertEquals(ErrorCode.INVALID_NULL_VALUE, exception.errorCode[3])
        assertEquals(ErrorCode.INVALID_LENGTH, exception.errorCode[4])
        assertEquals(ErrorCode.INVALID_SNILS_CHECKSUM, exception.errorCode[5])
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
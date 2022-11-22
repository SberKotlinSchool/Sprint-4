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
    fun `fail save client - validation phone error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assert(exception.errorCode.contains(ErrorCode.INVALID_PHONE))
        assertEquals(103, exception.errorCode[0].code)
    }

    @Test
    fun `fail save client - validation snils error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assert(exception.errorCode.contains(ErrorCode.INVALID_SNILS))
        assertEquals(102, exception.errorCode[0].code)
    }

    @Test
    fun `fail save client - validation email error`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assert(exception.errorCode.contains(ErrorCode.INVALID_EMAIL))
        assertEquals(101, exception.errorCode[0].code)
    }

    @Test
    fun `fail save client - validation firstname error`() {
        val client = getClientFromJson("/fail/user_with_bad_firstname.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assert(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER))
        assertEquals(100, exception.errorCode[0].code)
    }

    @Test
    fun `fail save client - validation lastname error`() {
        val client = getClientFromJson("/fail/user_with_bad_lastname.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assert(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER))
        assertEquals(100, exception.errorCode[0].code)
    }

    @Test
    fun `fail save client - validation full errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
        assert(exception.errorCode.contains(ErrorCode.INVALID_PHONE))
        assert(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER))
        assert(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER))
        assert(exception.errorCode.contains(ErrorCode.INVALID_EMAIL))
        assert(exception.errorCode.contains(ErrorCode.INVALID_SNILS))
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
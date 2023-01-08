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
        // по моему, перепутаны actual и expected
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[0])
    }

    @Test
    fun `fail save client - FirstName error validation`() {
        val client = getClientFromJson("/fail/new/user_bad_first_name.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[0])
    }

    @Test
    fun `fail save client - SecondName error validation`() {
        val client = getClientFromJson("/fail/new/user_bad_second_name.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.NULL_OR_EMPTY, exception.errorCode[0])
    }

    @Test
    fun `fail save client - PhoneNumber error validation`() {
        val client = getClientFromJson("/fail/new/user_bad_phone.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[0])
    }

    @Test
    fun `fail save client - СНИЛС error validation`() {
        val client = getClientFromJson("/fail/new/user_bad_snils.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_CHECK_SUM, exception.errorCode[0])
    }

    @Test
    fun `fail save client - Email error validation`() {
        val client = getClientFromJson("/fail/new/user_bad_email.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode[0])
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
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
        assertEquals(exception.message, ErrorCode.INVALID_FORMAT_PHONE.msg)
    }

    @Test
    fun `fail save client - validation name errors`() {
        val client = getClientFromJson("/fail/user_with_bad_first_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0].msg, ErrorCode.INVALID_FORMAT_NAME.msg)
    }

    @Test
    fun `fail save client - validation email error`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.message, ErrorCode.INVALID_FORMAT_EMAIL.msg)
    }

    @Test
    fun `fail save client - validation snils error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.message, ErrorCode.INVALID_FORMAT_SNILS.msg)
    }

    @Test
    fun `fail save client - validation snils sum error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_sum.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.message, ErrorCode.INVALID_FORMAT_SNILS_SUM.msg)
    }

    @Test
    fun `fail save client - validation null error`() {
        val client = getClientFromJson("/fail/user_with_value_null.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.message, ErrorCode.STRING_NULL_OR_BLANK.msg)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
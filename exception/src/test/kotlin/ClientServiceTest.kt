import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContains
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
        val e = assertThrows<ValidationException> {
            clientService.saveClient(client)
        }
        assertContains(e.errorCode[0], ErrorCode.NUMBER_LENGTH.msg)
    }

    @Test
    fun `fail save client - validation errors length`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        client.snils = "1"
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode.size, 6)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

    @Test
    fun `fail save client - validation error lastName max length`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        client.firstName = "test"
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertContains(exception.errorCode[0], ErrorCode.MAX_LENGTH.msg)
    }

    @Test
    fun `fail save client - validation error phone length`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        client.firstName = "test"
        client.lastName = "test"
        client.phone = "122"
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertContains(exception.errorCode[0], ErrorCode.NUMBER_LENGTH.msg)
    }

    @Test
    fun `fail save client - validation error phone regex`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        client.firstName = "test"
        client.lastName = "test"
        client.phone = "12345678901"
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertContains(exception.errorCode[0], ErrorCode.PHONE_NOT_MATCH.msg)
    }

    @Test
    fun `fail save client - validation error email regex`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        client.firstName = "test"
        client.lastName = "test"
        client.phone = "79110000000"
        client.email = "test"
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertContains(exception.errorCode[0], ErrorCode.EMAIL_NOT_MATCH.msg)
    }

    @Test
    fun `fail save client - validation error snils numbers only`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        client.firstName = "test"
        client.lastName = "test"
        client.phone = "79110000000"
        client.email = "test@test.tt"
        client.snils = "1234567890s"
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertContains(exception.errorCode[0], ErrorCode.NUM_ONLY.msg)
    }
}

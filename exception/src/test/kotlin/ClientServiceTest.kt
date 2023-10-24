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
        val client = getClientFromJson("success/client_valid_data.json")
        val version = client.version
        val result = clientService.saveClient(client)

        assertNotNull(result)
        assertEquals(version + 1, client.version)
    }

    @Test
    fun `fail save client - validation error - phone`() {
        val client = getClientFromJson("fail/client_invalid_phone.json")

        val ex = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(listOf(ErrorCode.INVALID_PHONE), ex.errorCode.asList())
    }

    @Test
    fun `fail save client - validation errors - email`() {
        val client = getClientFromJson("fail/client_invalid_email.json")
        val ex = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(listOf(ErrorCode.INVALID_EMAIL), ex.errorCode.asList())
    }

    @Test
    fun `fail save client - validation errors - firstName`() {
        val client = getClientFromJson("fail/client_invalid_first_name.json")
        val ex = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(listOf(ErrorCode.INVALID_NAME), ex.errorCode.asList())
    }

    @Test
    fun `fail save client - validation errors - lastName`() {
        val client = getClientFromJson("fail/client_invalid_last_name.json")
        val ex = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(listOf(ErrorCode.INVALID_NAME), ex.errorCode.asList())
    }

    @Test
    fun `fail save client - validation errors - SNILS`() {
        val client = getClientFromJson("fail/client_invalid_snils.json")
        val ex = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(listOf(ErrorCode.INVALID_SNILS), ex.errorCode.asList())
    }

    @Test
    fun `fail save client - validation errors - all fields`() {
        val client = Client(
                firstName = null,
                lastName = "007",
                phone = "+7(123) 111-00-00",
                email = "lol",
                snils = "463 436 384 96",
                version = 1)

        val ex = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(listOf(ErrorCode.INVALID_PHONE,
                ErrorCode.INVALID_NAME,
                ErrorCode.INVALID_NAME,
                ErrorCode.INVALID_EMAIL,
                ErrorCode.INVALID_SNILS), ex.errorCode.asList())
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
            .takeIf { it != null }
            ?.let { gson.fromJson(it.readText(), Client::class.java) }
            ?: throw Exception("Что-то пошло не так))")

}

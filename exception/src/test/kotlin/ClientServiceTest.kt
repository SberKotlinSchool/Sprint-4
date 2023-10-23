import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContentEquals
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
    fun `fail save client - multiply validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(ErrorCode.NULL_INPUT), exception.errorCodes["firstName"])
        assertContentEquals(listOf(ErrorCode.INVALID_LENGTH), exception.errorCodes["lastName"])
        assertContentEquals(
            listOf(ErrorCode.INVALID_LENGTH, ErrorCode.INVALID_CHARACTER),
            exception.errorCodes["phone"]
        )
        assertContentEquals(listOf(ErrorCode.INVALID_DOMAIN), exception.errorCodes["email"])
        assertContentEquals(listOf(ErrorCode.INVALID_CONTROL_NUMBER), exception.errorCodes["snils"])
    }

    @Test
    fun `fail save client - firstName validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_firstName.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(
            listOf(ErrorCode.INVALID_LENGTH, ErrorCode.INVALID_CHARACTER),
            exception.errorCodes["firstName"]
        )
    }

    @Test
    fun `fail save client - lastName validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_lastName.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(ErrorCode.NULL_INPUT), exception.errorCodes["lastName"])
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(
            listOf(ErrorCode.INVALID_LENGTH, ErrorCode.INVALID_CHARACTER),
            exception.errorCodes["phone"]
        )
    }

    @Test
    fun `fail save client - email validation error 1`() {
        val client = getClientFromJson("/fail/user_with_bad_email_1.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(ErrorCode.INVALID_DOMAIN), exception.errorCodes["email"])
    }

    @Test
    fun `fail save client - email validation error 2`() {
        val client = getClientFromJson("/fail/user_with_bad_email_2.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(ErrorCode.INVALID_DOMAIN), exception.errorCodes["email"])
    }

    @Test
    fun `fail save client - email validation error 3`() {
        val client = getClientFromJson("/fail/user_with_bad_email_2.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(ErrorCode.INVALID_DOMAIN), exception.errorCodes["email"])
    }

    @Test
    fun `fail save client - snils validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(ErrorCode.INVALID_CONTROL_NUMBER), exception.errorCodes["snils"])
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
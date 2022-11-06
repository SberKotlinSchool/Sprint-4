import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
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
    fun `fail save client - only phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val error = assertThrows<ValidationException>(ErrorCode.INVALID_PHONE.msg) {
            clientService.saveClient(client)
        }
        assertAll("У клиента ошибка только в телефоне",
            { assertTrue(error.message.equals(ErrorCode.INVALID_PHONE.msg)) },
            { assertTrue(error.errorCode.size == 1) },
            { assertEquals(error.errorCode[0], ErrorCode.INVALID_PHONE) }
        )
    }

    @Test
    fun `fail save client - lastName validation error`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val errors = assertThrows<ValidationException>(ErrorCode.INVALID_LAST_NAME.msg) {
            clientService.saveClient(client)
        }
        assertTrue(errors.errorCode.contains(ErrorCode.INVALID_LAST_NAME))
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val errors = assertThrows<ValidationException>(ErrorCode.INVALID_PHONE.msg) {
            clientService.saveClient(client)
        }
        assertTrue(errors.errorCode.contains(ErrorCode.INVALID_PHONE))
    }

    @Test
    fun `fail save client - email validation error`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val errors = assertThrows<ValidationException>(ErrorCode.INVALID_EMAIL.msg) {
            clientService.saveClient(client)
        }
        assertTrue(errors.errorCode.contains(ErrorCode.INVALID_EMAIL))
    }

    @Test
    fun `fail save client - snils validation error`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val errors = assertThrows<ValidationException>(ErrorCode.INVALID_SNILS.msg) {
            clientService.saveClient(client)
        }
        assertTrue(errors.errorCode.contains(ErrorCode.INVALID_SNILS))
    }

    @Test
    fun `fail save client - only snils validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val error = assertThrows<ValidationException>(ErrorCode.INVALID_SNILS.msg) {
            clientService.saveClient(client)
        }
        assertAll("У клиента ошибка только в СНИЛС",
            { assertTrue(error.message.equals(ErrorCode.INVALID_SNILS.msg)) },
            { assertTrue(error.errorCode.size == 1) },
            { assertEquals(error.errorCode[0], ErrorCode.INVALID_SNILS) }
        )
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
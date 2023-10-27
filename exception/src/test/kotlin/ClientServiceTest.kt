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
    fun `success save empty client`() {
        val client = getClientFromJson("/success/user_empty.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - all errors`() {
        val client = getClientFromJson("/fail/user_all_fields_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCodes.size)
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_PHONE, exception.errorCodes[0])
    }

    @Test
    fun `fail save client - name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_NAME, exception.errorCodes[0])
    }

    @Test
    fun `fail save client - surname validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_surname.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_NAME, exception.errorCodes[0])
    }

    @Test
    fun `fail save client - name and surname validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_name_and_surname.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(2, exception.errorCodes.size)
        assertEquals(ErrorCode.INVALID_NAME, exception.errorCodes[0])
        assertEquals(ErrorCode.INVALID_NAME, exception.errorCodes[1])
    }

    @Test
    fun `fail save client - snils validation error - illegal character`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_illegal_character.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_SNILS, exception.errorCodes[0])
    }

    @Test
    fun `fail save client - snils validation error - wrong checksum`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_wrong_checksum.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(ErrorCode.INVALID_SNILS, exception.errorCodes[0])
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
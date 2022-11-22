import com.google.gson.Gson
import org.junit.Test
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
        assertTrue(exception.errorCode.contains(ErrorCode.EMPTY) )
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_LENGTH_NAME_OR_SURNAME) )
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_EMAIL_PATTERN) )
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONE_COUNTRY) )
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_LENGTH_PHONE) )
    }


    @Test
    fun `fail save client - incorrect snils bit`() {
        val client = getClientFromJson("/fail/user_with_incorrect_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_SNILS_CHECKBIT))
    }

    @Test
    fun `fail save client - incorrect symbols`() {
        val client = getClientFromJson("/fail/user_incorrent_symbols.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER_NAMEorSURNAME))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER_EMAIL))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER_SNILS))
    }

    @Test
    fun `fail save client - too long fields`() {
        val client = getClientFromJson("/fail/user_with_too_long_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_LENGTH_NAME_OR_SURNAME))
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
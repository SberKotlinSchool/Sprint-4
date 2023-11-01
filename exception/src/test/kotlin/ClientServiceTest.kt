import com.google.gson.Gson
import org.junit.Test
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
    fun `fail save client - phone incorrect`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val errors: Array<ErrorCode> = arrayOf(ErrorCode.PHONE_NOT_CORRECT_LENGTH_ERROR, ErrorCode.PHONE_NOT_STARTS_FROM_7_OR_8_ERROR)
        assertTrue(errors contentEquals exception.errorCode)
    }

    @Test
    fun `fail save client - many errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val errors: Array<ErrorCode> = arrayOf(ErrorCode.FIRST_NAME_NULL_ERROR, ErrorCode.LAST_NAME_TOO_LONG_ERROR,
                ErrorCode.PHONE_NOT_CORRECT_LENGTH_ERROR, ErrorCode.PHONE_NOT_STARTS_FROM_7_OR_8_ERROR,
                ErrorCode.EMAIL_WRONG_FORMAT_ERROR, ErrorCode.SNILS_CHECKSUM_ERROR)
        assertTrue(errors contentEquals exception.errorCode)
    }

    @Test
    fun `fail save client - latin name`() {
        val client = getClientFromJson("/fail/user_with_not_cyrillic_name.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val errors: Array<ErrorCode> = arrayOf(ErrorCode.FIRST_NAME_NOT_CYRILLIC_ERROR, ErrorCode.LAST_NAME_NOT_CYRILLIC_ERROR)
        assertTrue(errors contentEquals exception.errorCode)
    }

    @Test
    fun `fail save client - too long email`() {
        val client = getClientFromJson("/fail/user_data_too_long_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val errors: Array<ErrorCode> = arrayOf(ErrorCode.EMAIL_TOO_LONG_ERROR)
        assertTrue(errors contentEquals exception.errorCode)
    }

    @Test
    fun `fail save client - corrupted snils`() {
        val client = getClientFromJson("/fail/user_corrupted_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val errors: Array<ErrorCode> = arrayOf(ErrorCode.SNILS_WRONG_LENGTH_ERROR, ErrorCode.SNILS_NOT_NUMBERS_ERROR)
        assertTrue(errors contentEquals exception.errorCode)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
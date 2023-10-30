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
        assertEquals(exception.errorCode.size, 6)
        assertEquals(exception.errorCode[0], ErrorCode.VALUE_IS_NULL)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_NAME_LENGTH)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_REGION)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_PHONE_LENGTH)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_EMAIL_DOMEN)
        assertEquals(exception.errorCode[5], ErrorCode.INVALID_SNILS_CONTROL_NUMBER)
    }

    @Test
    fun allVarsNull(){
        val client = getClientFromJson("/fail/null_user.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode.size, 5)
        assertEquals(exception.errorCode[0], ErrorCode.VALUE_IS_NULL)
        assertEquals(exception.errorCode[1], ErrorCode.VALUE_IS_NULL)
        assertEquals(exception.errorCode[2], ErrorCode.VALUE_IS_NULL)
        assertEquals(exception.errorCode[3], ErrorCode.VALUE_IS_NULL)
        assertEquals(exception.errorCode[4], ErrorCode.VALUE_IS_NULL)
    }

    @Test
    fun `incorrect letters in all fields`(){
        val client = getClientFromJson("/fail/incorrect_letters.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode.size, 7)
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NAME_LANGUAGE)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_NAME_LANGUAGE)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_REGION)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_PHONE_NUMS)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_EMAIL_LANGUAGE)
        assertEquals(exception.errorCode[5], ErrorCode.INVALID_EMAIL_DOMEN)
        assertEquals(exception.errorCode[6], ErrorCode.INVALID_SNILS_CHARACTER)
    }

    @Test
    fun `incorrect len in all fields`(){
        val client = getClientFromJson("/fail/incorrect_len_fields.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode.size, 7)
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NAME_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_NAME_LENGTH)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_REGION)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_PHONE_LENGTH)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_EMAIL_LENGTH)
        assertEquals(exception.errorCode[5], ErrorCode.INVALID_SNILS_LENGTH)
        assertEquals(exception.errorCode[6], ErrorCode.INVALID_SNILS_CHARACTER)
    }

    @Test
    fun `all errors`(){
        val client = getClientFromJson("/fail/all_errors.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode.size, 11)
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NAME_LENGTH)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_NAME_LANGUAGE)
        assertEquals(exception.errorCode[2], ErrorCode.VALUE_IS_NULL)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_REGION)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_PHONE_LENGTH)
        assertEquals(exception.errorCode[5], ErrorCode.INVALID_PHONE_NUMS)
        assertEquals(exception.errorCode[6], ErrorCode.INVALID_EMAIL_LENGTH)
        assertEquals(exception.errorCode[7], ErrorCode.INVALID_EMAIL_LANGUAGE)
        assertEquals(exception.errorCode[8], ErrorCode.INVALID_EMAIL_DOMEN)
        assertEquals(exception.errorCode[9], ErrorCode.INVALID_SNILS_LENGTH)
        assertEquals(exception.errorCode[10], ErrorCode.INVALID_SNILS_CHARACTER)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
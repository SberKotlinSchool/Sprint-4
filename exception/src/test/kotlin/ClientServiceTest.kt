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
    fun `validation - incorrect first name length - error INVALID_LENGTH_IN_NAME`() {
        val client = getClientFromJson("/fail/user_with_bad_first_name_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH_IN_NAME)
    }

    @Test
    fun `validation - incorrect first name characters - error INVALID_CHARACTER_IN_NAME`() {
        val client = getClientFromJson("/fail/user_with_bad_first_name_latin.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER_IN_NAME)
    }
    @Test
    fun `validation - incorrect second name length - error INVALID_LENGTH_IN_NAME`() {
        val client = getClientFromJson("/fail/user_with_bad_surname_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH_IN_NAME)
    }

    @Test
    fun `validation - incorrect second name characters - error INVALID_CHARACTER_IN_NAME`() {
        val client = getClientFromJson("/fail/user_with_bad_surname_latin.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER_IN_NAME)
    }

    @Test
    fun `validation - incorrect email length - error INVALID_LENGTH_IN_EMAIL`() {
        val client = getClientFromJson("/fail/user_with_bad_email_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH_IN_EMAIL)
    }

    @Test
    fun `validation - incorrect email characters - error INVALID_DOMAIN_IN_EMAIL`() {
        val client = getClientFromJson("/fail/user_with_bad_email_domain.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_DOMAIN_IN_EMAIL)
    }

    @Test
    fun `validation - incorrect phone length - error INVALID_LENGTH_IN_PHONE_NUMBER`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH_IN_PHONE_NUMBER)
    }

    @Test
    fun `validation - incorrect phone characters - error INVALID_CHARACTER_IN_PHONE_NUMBER`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_characters.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER_IN_PHONE_NUMBER)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_PHONE_NUMBER)
    }

    @Test
    fun `validation - incorrect snils length - error INVALID_LENGTH_IN_SNILS`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH_IN_SNILS)
    }

    @Test
    fun `validation - incorrect snils characters - error INVALID_CHARACTER_IN_SNILS`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_characters.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER_IN_SNILS)
    }

    @Test
    fun `validation - incorrect snils checksum - error INVALID_SNILS`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_checksum.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS)
    }

    @Test
    fun `validation - incorrect every fields - list of errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_LENGTH_IN_PHONE_NUMBER)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_PHONE_NUMBER)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_LENGTH_IN_NAME)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_LENGTH_IN_NAME)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_DOMAIN_IN_EMAIL)
        assertEquals(exception.errorCode[5], ErrorCode.INVALID_SNILS)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
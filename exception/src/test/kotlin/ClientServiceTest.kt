import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

open class ClientServiceTest {
    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    //99851974496
    @Test
    fun `phone validation error - not 7 or 8 at start`() {
        val client = getClientFromJson("/fail/phone/user_with_bad_phone2.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode.size, 1)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONE_CHARACTER))
    }

    //+79851974496
    @Test
    fun `phone validation errors - invalid character and length`() {
        val client = getClientFromJson("/fail/phone/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(2, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONE_LENGTH))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONE_CHARACTER))
    }

    @Test
    fun `fio validation errors - invalid character, length in lastname and name`() {
        val client = getClientFromJson("/fail/fio/user_with_bad_fio.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(2, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CLIENT_FIO))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_FIO_LENGTH))
    }

    @Test
    fun `snils validation errors - invalid control sum`() {
        val client = getClientFromJson("/fail/snils/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(1, exception.errorCode.size)
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CONTROL_SUM_SNILS)
    }

    @Test
    fun `snils validation errors - invalid characters and length`() {
        val client = getClientFromJson("/fail/snils/user_with_bad_snils_characters.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(2, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_SNILS_CHARACTER))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_SNILS_LENGTH))
    }

    @Test
    fun `email validation errors - bad email - invalid chars and length`() {
        val client = getClientFromJson("/fail/email/user_with_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(2, exception.errorCode.size)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_EMAIL))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_EMAIL_LENGTH))
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_FIO_LENGTH))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONE_LENGTH))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_EMAIL))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CONTROL_SUM_SNILS))
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
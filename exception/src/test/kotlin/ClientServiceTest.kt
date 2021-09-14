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
    fun `fail save client - validation error phone`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")

        val exception = assertThrows<ValidationException>("Недопустимая длина номера и символы") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0],ErrorCode.INVALID_PHONE_CHARACTER)
        assertEquals(exception.errorCode[1],ErrorCode.INVALID_PHONE_LENGTH)
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")

        val exception = assertFailsWith<ValidationException>("Куча самых разных ошибок") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_PHONE_CHARACTER)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_PHONE_LENGTH)
        assertEquals(exception.errorCode[2], ErrorCode.NULL_NAME)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_NAME_LENGTH)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_EMAIL)
        assertEquals(exception.errorCode[5], ErrorCode.INVALID_SNILS_CHECKSUM)
    }

    @Test
    fun `fail save client - validation snils checksum`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_checksum.json")

        val exception = assertThrows<ValidationException>("Неправильная контрольная сумма") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0],ErrorCode.INVALID_SNILS_CHECKSUM)
    }

    @Test
    fun `fail save client - validation email`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")

        val exception = assertThrows<ValidationException>("Неправильная буква (а) в слове mail") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0],ErrorCode.INVALID_EMAIL)
    }

    @Test
    fun `fail save client - validation lastname`() {
        val client = getClientFromJson("/fail/user_with_bad_lastname.json")

        val exception = assertThrows<ValidationException>("Не все на кирилице и слишком большая длина") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0],ErrorCode.INVALID_NAME_FORMAT)
        assertEquals(exception.errorCode[1],ErrorCode.INVALID_NAME_LENGTH)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
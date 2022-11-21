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
    fun `fail save client - phone error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER_IN_PHONENUMBER))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_PHONENUMBER_SIZE))
        assertEquals(2,exception.errorCode.size)
    }

    @Test
    fun `fail save client -  fio error`() {
        val client = getClientFromJson("/fail/user_with_bad_fio.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.filter { it == ErrorCode.INVALID_CHARACTER_IN_FIO }.size == 2)
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_CHARACTER_FIO_SIZE))
        assertEquals(3,exception.errorCode.size)
    }
    @Test
    fun `fail save client -  email error`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_EMAIL))
        assertEquals(1,exception.errorCode.size)
    }

    @Test
    fun `fail save client -  snils error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_SNILS_SIZE))
        assertTrue(exception.errorCode.contains(ErrorCode.INVALID_SNILS))
        assertEquals(3,exception.errorCode.size)
    }

    @Test
    fun `fail save client -  empty errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue(exception.errorCode.contains(ErrorCode.PHONENUMBER_IS_NULL))
        assertTrue(exception.errorCode.contains(ErrorCode.NAME_OR_LASTNAME_IS_NULL))
        assertTrue(exception.errorCode.contains(ErrorCode.NAME_OR_LASTNAME_IS_NULL))
        assertTrue(exception.errorCode.contains(ErrorCode.EMAIL_IS_NULL))
        assertTrue(exception.errorCode.contains(ErrorCode.SNILS_IS_NULL))
        assertEquals(5,exception.errorCode.size)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}

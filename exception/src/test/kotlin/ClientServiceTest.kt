import com.google.gson.Gson
import org.junit.Test
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
    fun `empty save client`() {
        val client = getClientFromJson("/fail/empty_user.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NUMBER)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_EMAIL)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_NAME)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_NAME)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_SNILS)
    }

    @Test
    fun `failed save client - validation errors except names`() {
        val client = getClientFromJson("/fail/corrupted_user_except_names.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NUMBER)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_EMAIL)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_SNILS)
    }

    @Test
    fun `failed save client - validation errors`() {
        val client = getClientFromJson("/fail/corrupted_user_data.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NUMBER)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_EMAIL)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_NAME)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_NAME)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_SNILS)
    }

    @Test
    fun `nullable save client`() {
        val client = getClientFromJson("/fail/nullable_user.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.NULL_VALUE)
        assertEquals(exception.errorCode[1], ErrorCode.NULL_VALUE)
        assertEquals(exception.errorCode[2], ErrorCode.NULL_VALUE)
        assertEquals(exception.errorCode[3], ErrorCode.NULL_VALUE)
        assertEquals(exception.errorCode[4], ErrorCode.NULL_VALUE)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
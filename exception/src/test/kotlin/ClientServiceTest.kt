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
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>{
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(2, exception.errorCode.find { it.first == "phone" }!!.second.size)
        assert(exception.errorCode.find { it.first == "phone" }!!.second
                .containsAll(listOf(ErrorCode.INVALID_CHARACTER, ErrorCode.INVALID_LENGTH)))
    }
    @Test
    fun `fail save client - name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_name.json")
        val exception = assertFailsWith<ValidationException>{
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(1, exception.errorCode.find { it.first == "firstName" }!!.second.size)
        assertEquals(ErrorCode.INVALID_CHARACTER,
                exception.errorCode.find { it.first == "firstName" }!!.second.first())
    }

    @Test
    fun `fail save client - lastname validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_lastname.json")
        val exception = assertFailsWith<ValidationException>{
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(2, exception.errorCode.find { it.first == "lastName" }!!.second.size)
        assert(exception.errorCode.find { it.first == "lastName" }!!.second.containsAll(listOf(ErrorCode.INVALID_CHARACTER, ErrorCode.INVALID_LENGTH)))
    }

    @Test
    fun `fail save client - mail validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_mail.json")
        val exception = assertFailsWith<ValidationException>{
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(1, exception.errorCode.find { it.first == "email" }!!.second.size)
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode.find { it.first == "email" }!!.second.first())
    }

    @Test
    fun `fail save client - snils validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>{
            clientService.saveClient(client)
        }
        assertEquals(1, exception.errorCode.size)
        assertEquals(1, exception.errorCode.find { it.first == "snils" }!!.second.size)
        assertEquals(ErrorCode.INVALID_CONTROL_NUMBER, exception.errorCode.find { it.first == "snils" }!!.second.first())
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }
        assertEquals(5, exception.errorCode.size)
        assertEquals(1, exception.errorCode.find { it.first == "firstName" }!!.second.size)
        assertEquals(ErrorCode.NULL_VALUE, exception.errorCode.find { it.first == "firstName" }!!.second.first())
        assertEquals(1, exception.errorCode.find { it.first == "lastName" }!!.second.size)
        assertEquals(ErrorCode.INVALID_LENGTH, exception.errorCode.find { it.first == "lastName" }!!.second.first())
        assertEquals(2, exception.errorCode.find { it.first == "phone" }!!.second.size)
        assert(exception.errorCode.find { it.first == "phone" }!!.second
                .containsAll(listOf(ErrorCode.INVALID_CHARACTER, ErrorCode.INVALID_LENGTH)))
        assertEquals(1, exception.errorCode.find { it.first == "email" }!!.second.size)
        assertEquals(ErrorCode.INVALID_CHARACTER, exception.errorCode.find { it.first == "email" }!!.second.first())
        assertEquals(2, exception.errorCode.find { it.first == "snils" }!!.second.size)
        assert(exception.errorCode.find { it.first == "snils" }!!.second
                .containsAll(listOf(ErrorCode.INVALID_CONTROL_NUMBER, ErrorCode.INVALID_LENGTH)))
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
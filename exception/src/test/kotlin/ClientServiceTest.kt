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
    fun `fail save client - phone error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(ErrorCode.INVALID_START_CHAR_PHONE)
        val resultList = exception.errorCode.toList()
        assertTrue(exceptionList.containsAll(resultList) && resultList.containsAll(exceptionList))
    }
    @Test
    fun `fail save client - snils error`() {
        val client = getClientFromJson("/fail/user_with_bad_SNILS.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(ErrorCode.SUM_SNILS_ERROR)
        val resultList = exception.errorCode.toList()
        assertTrue(exceptionList.containsAll(resultList) && resultList.containsAll(exceptionList))
    }
    @Test
    fun `fail save client - email error`() {
        val client = getClientFromJson("/fail/user_with_bad_EMAIL.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(ErrorCode.INVALID_DOMAIN, ErrorCode.INVALID_CHARACTER)
        val resultList = exception.errorCode.toList()
        assertTrue(exceptionList.containsAll(resultList) && resultList.containsAll(exceptionList))
    }
    @Test
    fun `fail save client - firstname error`() {
        val client = getClientFromJson("/fail/user_with_bad_FN.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(ErrorCode.INVALID_LENGTH_FN, ErrorCode.INVALID_CHARACTER)
        val resultList = exception.errorCode.toList()
        assertTrue(exceptionList.containsAll(resultList) && resultList.containsAll(exceptionList))
    }
    @Test
    fun `fail save client - lastname error`() {
        val client = getClientFromJson("/fail/user_with_bad_LN.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        val exceptionList = listOf(ErrorCode.INVALID_LENGTH_LN, ErrorCode.INVALID_CHARACTER)
        val resultList = exception.errorCode.toList()
        assertTrue(exceptionList.containsAll(resultList) && resultList.containsAll(exceptionList))
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
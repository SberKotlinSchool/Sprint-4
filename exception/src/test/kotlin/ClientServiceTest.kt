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
    fun `fail save client - empty data`() {
        val client = getClientFromJson("/fail/user_null_fields.json")
        val exception = assertFailsWith<ValidationException>("Пустая строка") {
            clientService.saveClient(client)
        }
        exception.errorCode.forEach {
            assertEquals(ErrorCode.EMPTY_INPUT, it)
        }
    }

    @Test
    fun `fail save client - null data`() {
        val client = getClientFromJson("/fail/user_null_fields.json")
        val exception = assertFailsWith<ValidationException>("Пустая строка") {
            clientService.saveClient(client)
        }
        exception.errorCode.forEach {
            assertEquals(ErrorCode.EMPTY_INPUT, it)
        }
    }

    @Test
    fun `fail save client - all errors`() {
        val client = getClientFromJson("/fail/user_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ошибка валидации имени") {
            clientService.saveClient(client)
        }
        exception.errorCode.forEachIndexed { index, errorCode ->
            assertEquals(ErrorCode.values()[index+1], errorCode)
        }
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")
}
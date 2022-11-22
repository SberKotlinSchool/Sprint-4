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
    fun `fail null value - validation error`() {
        val client = getClientFromJson("/fail/bad_null.json")
        val exception = assertFailsWith<ValidationException>("Все : Поле не должно быть пустым") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NULL_F)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_NULL_L)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_NULL_N)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_NULL_D)
        assertEquals(exception.errorCode[4], ErrorCode.INVALID_NULL_S)
    }

    @Test
    fun `fail name_lastname - validation error`() {
        val client = getClientFromJson("/fail/bad_name.json")
        val exception = assertFailsWith<ValidationException>("Имя и Фамилия : Ошибки с раскладкой и кол-вом символов") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_FIRSTNAME)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_COUNT_CHAR_F)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_LASTTNAME)
        assertEquals(exception.errorCode[3], ErrorCode.INVALID_COUNT_CHAR_L)
    }

    @Test
    fun `fail numberphone - validation error`() {
        val client = getClientFromJson("/fail/bad_numbers.json")
        val exception = assertFailsWith<ValidationException>("Номер телефона : Проблемы с раскладкой, кол-вом и стартовым символом") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_NUMBER_PHONE)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_START_NUMBER)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_COUNT_CHAR_N)
    }
    @Test
    fun `fail mail - validation error`() {
        val client = getClientFromJson("/fail/bad_mail.json")
        val exception = assertFailsWith<ValidationException>("Электронная почта : Проблемы с раскладкой, кол-вом и структурой") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_MAILNAME)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_DOMEN)
        assertEquals(exception.errorCode[2], ErrorCode.INVALID_COUNT_CHAR_D)
    }

    @Test
    fun `fail snils with good controlnumber - validation error`() {
        val client = getClientFromJson("/fail/bad_snils.json")
        val exception = assertFailsWith<ValidationException>("CНИЛС : Проблемы с раскладкой и кол-вом") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_SNILS_NUMBER)
        assertEquals(exception.errorCode[1], ErrorCode.INVALID_COUNT_CHAR_S)
    }

    @Test
    fun `fail controlnumber in snils - validation error`() {
        val client = getClientFromJson("/fail/bad_controlnumber_snils.json")
        val exception = assertFailsWith<ValidationException>("СНИЛС : Некорректное контрольное число") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CONTROL)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
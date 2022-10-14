import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private var clientService = ClientService()

    //**Имя и Фамилия** - только кириллица, не более 16 символов каждое поле <br>
    //**Телефон** - начинается с 7 или 8ки, только цифры, 11 знаков <br>
    //**Email** - латиница, с валидацией @имя_домена, не более 32 символов <br>
    //**СНИЛС** - только цифры, 11 символов, с валидацией Контрольного числа <br>

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - data is empty`() {
        val client = getClientFromJson("/fail/user_data_empty.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode.size, 5)
        exception.errorCode.forEach {
            assertEquals(it, ErrorCode.EMPTY_INPUT)
        }
    }

    @Test
    fun `fail save client - snils check number wrong`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.SNILS_CHECK_NUMBER)
    }

    @Test
    fun `fail save client - data too long`() {
        val client = getClientFromJson("/fail/user_data_too_long.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode
            .filter {it == ErrorCode.LENGTH }
            .size,
            5
        )
    }

    @Test
    fun `fail save client - invalid characters`() {
        val client = getClientFromJson("/fail/user_with_invalid_characters.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode
            .filter {it == ErrorCode.INVALID_CHARACTER }
            .size,
            5
        )
    }

    @Test
    fun `fail save client - email domain missing`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.DOMAIN)
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
        assertEquals(exception.errorCode[0], ErrorCode.EMPTY_INPUT)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
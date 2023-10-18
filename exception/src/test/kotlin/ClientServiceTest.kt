import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertContains
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @ParameterizedTest
    @ValueSource(strings = ["/success/user.json", "/success/user2.json", "/success/user3.json"])
    fun `success save client`(source: String) {
        //given
        val client = getClientFromJson(source)
        //when
        val result = clientService.saveClient(client)
        //then
        assertNotNull(result)
    }

    @ParameterizedTest
    @CsvSource(
        "/fail/user_with_bad_phone.json, INVALID_PHONE",
        "/fail/user_with_bad_email.json, INVALID_EMAIL",
        "/fail/user_with_bad_name.json, INVALID_NAME",
        "/fail/user_with_bad_phone2.json, INVALID_PHONE",
        "/fail/user_with_bad_snils.json, INVALID_SNILS",
        "/fail/user_with_null_email.json, INVALID_NULL_OR_EMPTY",
        "/fail/user_with_null_name.json, INVALID_NULL_OR_EMPTY",
        "/fail/user_with_null_phone.json, INVALID_NULL_OR_EMPTY",
        "/fail/user_with_null_snils.json, INVALID_NULL_OR_EMPTY",
    )
    fun `fail save client - validation error`(source: String, code: ErrorCode) {
        //given
        val client = getClientFromJson(source)
        //when
        //then
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContains(exception.errorCode, code)
    }

    @Test
    fun `fail save client - validation errors`() {
        //given
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        //when
        //then
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(
            exception.errorCode,
            arrayOf(
                ErrorCode.INVALID_NULL_OR_EMPTY,
                ErrorCode.INVALID_NAME,
                ErrorCode.INVALID_PHONE,
                ErrorCode.INVALID_SNILS
            )
        )
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
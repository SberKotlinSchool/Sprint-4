import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()
    private val expectedErrorsAllFieldsEmpty = listOf(
        ErrorCode.MAIL_IS_MISSING,
        ErrorCode.FIRST_NAME_IS_MISSING,
        ErrorCode.PHONE_IS_MISSING,
        ErrorCode.LAST_NAME_IS_MISSING,
        ErrorCode.SNILS_IS_MISSING,
    )
    private val expectedErrorsAllFields = listOf(
        ErrorCode.INVALID_MAIL,
        ErrorCode.INVALID_FIRST_NAME,
        ErrorCode.INVALID_PHONE,
        ErrorCode.INVALID_LAST_NAME,
        ErrorCode.INVALID_SNILS,
    )

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation errors missing data`() {
        val client = getClientFromJson("/fail/user_with_missing_data.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertTrue { exception.errorCode.intersect(expectedErrorsAllFieldsEmpty).size == exception.errorCode.size }
    }

    @Test
    fun `fail save client - validation errors wrong fields length`() {
        val client = getClientFromJson("/fail/user_with_bad_data_length.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertTrue { exception.errorCode.intersect(expectedErrorsAllFields).size == exception.errorCode.size }
    }

    @Test
    fun `fail save client - validation errors wrong fields symbols`() {
        val client = getClientFromJson("/fail/user_with_bad_data_symbols.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertTrue { exception.errorCode.intersect(expectedErrorsAllFields).size == exception.errorCode.size }
    }
    @Test
    fun `fail save client - validation error invalid phone start`() {
        val client = getClientFromJson("/fail/user_with_bad_phone_start.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertTrue { exception.errorCode.first() == ErrorCode.INVALID_PHONE }
    }

    @Test
    fun `fail save client - validation error email hast domain name`() {
        val client = getClientFromJson("/fail/user_with_bad_email_domain.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertTrue { exception.errorCode.first() == ErrorCode.INVALID_MAIL }
    }

    @Test
    fun `fail save client - validation error snils wrong control sum`() {
        val client = getClientFromJson("/fail/user_with_bad_snils_control_sum.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(exception.errorCode.toList(), listOf(ErrorCode.INVALID_SNILS_SUM) )
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
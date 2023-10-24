import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
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

    @ParameterizedTest
    @MethodSource("provideTestParameters")
    fun `saveClient should throw exception with particular error code`(fileName: String, expectedErrorCode: ErrorCode) {
        // given
        val client = getClientFromJson(fileName)

        // when
        val exception = assertFailsWith<ValidationException>(
            "Test should throw exception with code = $expectedErrorCode"
        ) {
            clientService.saveClient(client)
        }

        // then
        assertTrue(exception.errorCode.size == 1)
        assertEquals(expectedErrorCode, exception.errorCode.single())
    }

    @Test
    fun `saveClient should throw exception with multiple error codes`() {
        // given
        val client = getClientFromJson("/fail/user_data_corrupted.json")

        // when
        val exception = assertFailsWith<ValidationException> { clientService.saveClient(client) }

        // then
        assertTrue(exception.errorCode.isNotEmpty())
        assertTrue(exception.errorCode.size > 1)
        println(exception.errorCode.toList())
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

    companion object {
        @JvmStatic
        fun provideTestParameters() = listOf(
            Arguments.of("/fail/user_with_bad_phone_invalid_len.json", ErrorCode.INVALID_PHONE_LENGTH),
            Arguments.of("/fail/user_with_bad_phone_invalid_start.json", ErrorCode.INVALID_PHONE_START_DIGIT),
            Arguments.of("/fail/user_with_bad_phone_invalid_symbol.json", ErrorCode.INVALID_PHONE_CHARACTER),
            Arguments.of("/fail/user_with_bad_phone_is_missing.json", ErrorCode.PHONE_IS_MISSING),
            Arguments.of("/fail/user_with_bad_mail_domain_is_missing.json", ErrorCode.MAIL_DOMAIN_IS_MISSING),
            Arguments.of("/fail/user_with_bad_mail_invalid_character.json", ErrorCode.INVALID_MAIL_LANGUAGE),
            Arguments.of("/fail/user_with_bad_mail_invalid_len.json", ErrorCode.INVALID_MAIL_LENGTH),
            Arguments.of("/fail/user_with_bad_mail_is_missing.json", ErrorCode.MAIL_ADDRESS_IS_MISSING),
            Arguments.of("/fail/user_with_bad_name_invalid_character.json", ErrorCode.INVALID_NAME_LANGUAGE),
            Arguments.of("/fail/user_with_bad_name_invalid_len.json", ErrorCode.INVALID_NAME_LENGTH),
            Arguments.of("/fail/user_with_bad_name_is_missing.json", ErrorCode.NAME_IS_MISSING),
            Arguments.of("/fail/user_with_bad_snils_invalid_character.json", ErrorCode.INVALID_SNILS_CHARACTER),
            Arguments.of("/fail/user_with_bad_snils_invalid_control_number.json", ErrorCode.INVALID_SNILS_CONTROL_NUMBER),
            Arguments.of("/fail/user_with_bad_snils_invalid_len.json", ErrorCode.INVALID_SNILS_LENGTH),
            Arguments.of("/fail/user_with_bad_snils_is_missing.json", ErrorCode.SNILS_IS_MISSING)
        )
    }

}
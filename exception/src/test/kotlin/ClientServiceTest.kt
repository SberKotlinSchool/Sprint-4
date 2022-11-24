import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
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

    private companion object {
        @JvmStatic
        fun failsArguments() = Stream.of(
            Arguments.of( ErrorCode.INVALID_CYRILLIC, "/fail/user_data_corrupted.json")
            , Arguments.of(ErrorCode.INVALID_PHONE_NUMBER, "/fail/user_with_bad_phone.json")
            , Arguments.of(ErrorCode.INVALID_SNILS, "/fail/user_with_bad_snils.json")
            , Arguments.of(ErrorCode.INVALID_CYRILLIC, "/fail/user_with_bad_name.json")
            , Arguments.of(ErrorCode.INVALID_EMAIL, "/fail/user_with_bad_email.json")
        )
    }

    @MethodSource("failsArguments")
    @ParameterizedTest
    fun `fail save client - validation errors full`(expected: ErrorCode, path: String) {
        val client = getClientFromJson(path)
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(expected, exception.errorCode[0])
    }



    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
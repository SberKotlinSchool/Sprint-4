import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertContains
import kotlin.test.assertEquals
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


    @ParameterizedTest
    @MethodSource("provider")
    fun `fail save client - validation error`(kit: Pair<String, List<ErrorCode>>) {
        val client = getClientFromJson("/fail/${kit.first}")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        kit.second.forEach {
            assertContains(exception.errorCode, it)
        }
        assertEquals(exception.errorCode.size, kit.second.size)
    }
    companion object {
        @JvmStatic
        fun provider(): Stream<Pair<String, List<ErrorCode>>> = Stream.of(
                Pair("user_with_bad_phone.json", listOf(ErrorCode.INVALID_CHARACTER_PHONE, ErrorCode.INVALID_LENGTH_PHONE)),
                Pair("user_with_bad_phone_character.json", listOf(ErrorCode.INVALID_CHARACTER_PHONE)),
                Pair("user_data_corrupted.json", listOf(ErrorCode.INVALID_LENGTH_SNILS, ErrorCode.INVALID_CHARACTER_EMAIL, ErrorCode.INVALID_LENGTH_FIRST_NAME, ErrorCode.INVALID_LENGTH_LAST_NAME, ErrorCode.INVALID_CHARACTER_PHONE, ErrorCode.INVALID_LENGTH_PHONE)),
                Pair("user_with_bad_snils_check_number.json", listOf(ErrorCode.INVALID_CHECK_NUMBER_SNILS)),
                Pair("user_with_bad_snils_length.json", listOf(ErrorCode.INVALID_LENGTH_SNILS)),
                Pair("user_with_bad_snils.json", listOf(ErrorCode.INVALID_CHARACTER_SNILS)),
                Pair("user_with_bad_lastname.json", listOf(ErrorCode.INVALID_LENGTH_LAST_NAME, ErrorCode.INVALID_CHARACTER_LAST_NAME)),
                Pair("user_with_bad_name.json", listOf(ErrorCode.INVALID_LENGTH_FIRST_NAME, ErrorCode.INVALID_CHARACTER_FIRST_NAME)),
                Pair("user_with_bad_email.json", listOf(ErrorCode.INVALID_CHARACTER_EMAIL, ErrorCode.INVALID_LENGTH_EMAIL)),
        )
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
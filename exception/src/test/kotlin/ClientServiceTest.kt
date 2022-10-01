import com.google.gson.Gson
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @ParameterizedTest(name = "{index}) with fileName: {arguments}")
    @ValueSource(strings = ["/success/firstUser.json", "/success/secondUser.json"])
    fun `success save client`(fileName: String) {
        val client = getClientFromJson(fileName)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @ParameterizedTest(name = "{index}): {argumentsWithNames}")
    @MethodSource("getDataForFail")
    fun `fail save client - validation error`(client: Client, listOfErrors: List<ErrorCode>) {
        val exception = assertFailsWith<ValidationException>("Ошибка") {
            clientService.saveClient(client)
        }
        assertNotNull(exception.errorCode)
        assertEquals(exception.errorCode.size, listOfErrors.size)
        listOfErrors.forEach { error -> assertTrue(exception.errorCode.contains(error)) }
    }

    private fun getDataForFail() = Stream.of(
        Arguments.of(
            getClientFromJson("/fail/user_with_bad_phone.json"),
            listOf(ErrorCode.INVALID_PHONE_CHARACTER, ErrorCode.INVALID_PHONE_LENGTH)
        ),
        Arguments.of(
            getClientFromJson("/fail/user_data_corrupted.json"),
            listOf(
                ErrorCode.EMPTY_NAME, ErrorCode.INVALID_SURNAME_LENGTH, ErrorCode.INVALID_PHONE_CHARACTER,
                ErrorCode.INVALID_PHONE_LENGTH, ErrorCode.INVALID_SNILS_CHECKSUM, ErrorCode.INVALID_EMAIL_CHARACTER
            )
        ),
        Arguments.of(
            getClientFromJson("/fail/user_with_latin_surname_and_long_phone.json"),
            listOf(ErrorCode.INVALID_SURNAME_CHARACTER, ErrorCode.INVALID_PHONE_LENGTH)
        ),
        Arguments.of(
            getClientFromJson("/fail/user_with_latin_name_and_long_email.json"),
            listOf(ErrorCode.INVALID_NAME_CHARACTER, ErrorCode.INVALID_EMAIL_LENGTH)
        ),
        Arguments.of(
            getClientFromJson("/fail/user_with_empty_data.json"),
            listOf(
                ErrorCode.EMPTY_SURNAME,
                ErrorCode.EMPTY_NAME,
                ErrorCode.EMPTY_SNILS,
                ErrorCode.EMPTY_EMAIL,
                ErrorCode.EMPTY_PHONE
            )
        )
    )


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
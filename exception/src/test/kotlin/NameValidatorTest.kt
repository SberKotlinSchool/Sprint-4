import com.google.gson.Gson
import io.mockk.mockk
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class NameValidatorTest {
    companion object {
        @JvmStatic
        fun getInvalidPhone() = listOf(
            Arguments.of("723", ErrorCode.INVALID_PHONE_LENGTH),
            Arguments.of("890571253555", ErrorCode.INVALID_PHONE_LENGTH),
            Arguments.of("39057125355", ErrorCode.INVALID_PHONE_NUMBER),
            Arguments.of("abcdefghig!", ErrorCode.INVALID_PHONE_NUMBER)
        )

//        @JvmStatic
//        fun getTueThu() = listOf(
//            Arguments.of("2022-11-01T00:15:30.00Z"),
//            Arguments.of("2022-11-03T00:15:30.00Z")
//        )
//
//        @JvmStatic
//        fun getWeekend() = listOf(
//            Arguments.of("2022-11-05T00:15:30.00Z"),
//            Arguments.of("2022-11-06T00:15:30.00Z")
//        )
    }

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `fail save client - validation errors  firstName, lastName`() {
        val client = getClientFromJson("/fail/user_invalid_name.json")
        val exception =
            assertFailsWith<ValidationException>("Не получено ожидаемое исключение ValidationException при проверке firstName и lastName") {
                clientService.saveClient(client)
            }
        val errorCodeList = exception.errorCode
        assertTrue(errorCodeList.contains(ErrorCode.INVALID_LENGTH))
        assertTrue(errorCodeList.contains(ErrorCode.INVALID_CHARACTER))
    }

    @ParameterizedTest
    @MethodSource("getInvalidPhone")
    fun `fail save client - phone validation errors`(phone: String, errorCode: ErrorCode) {
        val client = Client("Ivan", "Ivanov", phone, "ivanov@mail.ru", "56553713311", 1)
        val exception =
            assertFailsWith<ValidationException>("Не получено ожидаемое исключение ValidationException при проверке firstName и lastName") {
                clientService.saveClient(client)
            }
        val errorCodeList = exception.errorCode
        assertTrue(errorCodeList.contains(errorCode))
    }


    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
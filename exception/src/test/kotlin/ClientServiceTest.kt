import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ClientServiceTest {
    companion object {

        @JvmStatic
        fun getInvalidPhone() = listOf(
            Arguments.of("723", ErrorCode.INVALID_PHONE_LENGTH),
            Arguments.of("890571253555", ErrorCode.INVALID_PHONE_LENGTH),
            Arguments.of("39057125355", ErrorCode.INVALID_PHONE_NUMBER),
            Arguments.of("abcdefghig!", ErrorCode.INVALID_PHONE_NUMBER)
        )

        // Имя и Фамилия - только кириллица, не более 16 символов каждое поле
        @JvmStatic
        fun getInvalidName() = listOf(
            Arguments.of("ИвановИвановИвановИвановИвановИвановИвановИвановИванов", ErrorCode.INVALID_LENGTH),
            Arguments.of("abcdefghig", ErrorCode.INVALID_CHARACTER)
        )

        //Email - латиница, с валидацией @имя_домена, не более 32 символов
        @JvmStatic
        fun getInvalidEmail() = listOf(
            Arguments.of("иванов@мэйл.ру", ErrorCode.INVALID_EMAIL),
            Arguments.of("ivanovmail.ru", ErrorCode.INVALID_EMAIL),
            Arguments.of("ivanov@mail", ErrorCode.INVALID_EMAIL),
            Arguments.of("ivanovivanovivanovivanovivanovivanovivanovivanov@mail.ru", ErrorCode.INVALID_EMAIL)
        )

        //СНИЛС - только цифры, 11 символов, с валидацией Контрольного числа
        @JvmStatic
        fun getInvalidSnils() = listOf(
            Arguments.of("ивановаснил", ErrorCode.INVALID_SNILS),
            Arguments.of("1234", ErrorCode.INVALID_SNILS),
            Arguments.of("123456765456", ErrorCode.INVALID_SNILS),
            Arguments.of("12345676545", ErrorCode.INVALID_SNILS_CHECKSUM)
        )
    }

    private val gson = Gson()
    private val clientService = ClientService()

    @ParameterizedTest
    @MethodSource("getInvalidName")
    fun `fail save client - validation errors  firstName`(firstName: String, errorCode: ErrorCode) {
        val client = Client(firstName, "Ivanov", "89057125358", "ivanov@mail.ru", "56553713311", 1)
        val exception =
            assertFailsWith<ValidationException>("Не получено ожидаемое исключение ValidationException при проверке firstName и lastName") {
                clientService.saveClient(client)
            }
        val errorCodeList = exception.errorCode
        assertTrue(errorCodeList.contains(errorCode))
    }

    @ParameterizedTest
    @MethodSource("getInvalidName")
    fun `fail save client - validation errors  lastName`(lastName: String, errorCode: ErrorCode) {
        val client = Client("Ivan", lastName, "89057125358", "ivanov@mail.ru", "56553713311", 1)
        val exception =
            assertFailsWith<ValidationException>("Не получено ожидаемое исключение ValidationException при проверке firstName и lastName") {
                clientService.saveClient(client)
            }
        val errorCodeList = exception.errorCode
        assertTrue(errorCodeList.contains(errorCode))
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

    @ParameterizedTest
    @MethodSource("getInvalidEmail")
    fun `fail save client - email validation errors`(email: String, errorCode: ErrorCode) {
        val client = Client("Ivan", "Ivanov", "89057125358", email, "56553713311", 1)
        val exception =
            assertFailsWith<ValidationException>("Не получено ожидаемое исключение ValidationException при проверке firstName и lastName") {
                clientService.saveClient(client)
            }
        val errorCodeList = exception.errorCode
        assertTrue(errorCodeList.contains(errorCode))
    }

    @ParameterizedTest
    @MethodSource("getInvalidSnils")
    fun `fail save client - snils validation errors`(snils: String, errorCode: ErrorCode) {
        val client = Client("Ivan", "Ivanov", "89057125358", "ivanov@mail.ru", snils, 1)
        val exception =
            assertFailsWith<ValidationException>("Не получено ожидаемое исключение ValidationException при проверке firstName и lastName") {
                clientService.saveClient(client)
            }
        val errorCodeList = exception.errorCode
        assertTrue(errorCodeList.contains(errorCode))
    }

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")
}
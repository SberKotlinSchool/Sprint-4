import com.google.gson.Gson
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    // region phone validation tests
    @ParameterizedTest(name = "#{index} - phone {0}")
    @ValueSource(strings = ["70123456789", "80123456789"])
    fun `success save client - correct phone`(phone: String) {
        val client = getClientFromJson("/success/user.json")
        setField(client, "phone", phone)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @ParameterizedTest(name = "#{index} - phone {0} - {2}")
    @MethodSource("incorrectPhoneTestArgs")
    fun `save client - incorrect phone`(phone: String, code: Number, msg: String, value: Int?, constraint: Int?) {
        val client = getClientFromJson("/success/user.json")
        val validatingProperty = "phone"
        unsuccessPropertyValidationTest(client, validatingProperty, phone, value, code, msg, constraint)
    }

    private fun incorrectPhoneTestArgs() : Stream<Arguments> {
        return Stream.of(
            Arguments.of("", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 0, 11),  // too short
            Arguments.of("           ", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 0, 11),  // too short
            Arguments.of("1234", ErrorCode.LENGTH_NOT_EQUAL.code, ErrorCode.LENGTH_NOT_EQUAL.msg, 4, 11),  // too short
            Arguments.of("123456789012345", ErrorCode.LENGTH_NOT_EQUAL.code, ErrorCode.LENGTH_NOT_EQUAL.msg, 15, 11),  // too long
            Arguments.of("01234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("11234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("21234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("31234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("41234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("51234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("61234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("91234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("01234567890", ErrorCode.PHONE_WRONG_FIRST_NUMBER.code, ErrorCode.PHONE_WRONG_FIRST_NUMBER.msg, null, null),  // 1st number
            Arguments.of("7123456789a", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("712345678A0", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("7123456_890", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("712345@7890", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("71234.67890", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("7fgkl67$%_+", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
        )
    }
    //endregion

    // region name-surname validation tests
    @ParameterizedTest(name = "#{index} - name {0}")
    @ValueSource(strings = ["Ян", "Алексей", "Абвгдежзийклмноп"])
    fun `success save client - correct name`(name: String) {
        val client = getClientFromJson("/success/user.json")
        setField(client, "firstName", name)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @ParameterizedTest(name = "#{index} - name {0} - {2}")
    @MethodSource("incorrectNameSurnameTestArgs")
    fun `save client - incorrect name`(name: String, code: Number, msg: String, value: Int?, constraint: Int?) {
        val client = getClientFromJson("/success/user.json")
        val validatingProperty = "firstName"
        unsuccessPropertyValidationTest(client, validatingProperty, name, value, code, msg, constraint)
    }

    @ParameterizedTest(name = "#{index} - surname {0}")
    @ValueSource(strings = ["Ян", "Алексей", "Абвгдежзийклмноп"])
    fun `success save client - correct surname`(lastName: String) {
        val client = getClientFromJson("/success/user.json")
        setField(client, "lastName", lastName)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @ParameterizedTest(name = "#{index} - surname {0} - {2}")
    @MethodSource("incorrectNameSurnameTestArgs")
    fun `save client - incorrect surname`(lastName: String, code: Number, msg: String, value: Int?, constraint: Int?) {
        val client = getClientFromJson("/success/user.json")
        val validatingProperty = "lastName"
        unsuccessPropertyValidationTest(client, validatingProperty, lastName, value, code, msg, constraint)
    }

    private fun incorrectNameSurnameTestArgs() : Stream<Arguments> {
        return Stream.of(
            Arguments.of("", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 0, 1),  // too short
            Arguments.of("    ", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 0, 1),  // too short
            Arguments.of("абвг", ErrorCode.NAME_WRONG_FIRST_LETTER.code, ErrorCode.NAME_WRONG_FIRST_LETTER.msg, null, null),  // 1st letter
            Arguments.of("абвгдеёжзиабвгдеёжзи", ErrorCode.LENGTH_TOO_LONG.code, ErrorCode.LENGTH_TOO_LONG.msg, 20, 16),  // too long
            Arguments.of("Zабвг", ErrorCode.NAME_WRONG_FIRST_LETTER.code, ErrorCode.NAME_WRONG_FIRST_LETTER.msg, null, null),  // 1st letter
            Arguments.of("fабвг", ErrorCode.NAME_WRONG_FIRST_LETTER.code, ErrorCode.NAME_WRONG_FIRST_LETTER.msg, null, null),  // 1st letter
            Arguments.of("Абвгдz", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("АбвгдЁ", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("Абвгд8", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("Абвгд$", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("Абвгд_", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("Абвгд.", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("Абвгд()*&%$", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
            Arguments.of("Абвгдsjdfh", ErrorCode.INVALID_CHARACTERS.code, ErrorCode.INVALID_CHARACTERS.msg, null, null),  // not digits
        )
    }
    //endregion

    // region phone validation tests
    @ParameterizedTest(name = "#{index} - email {0}")
    @ValueSource(strings = ["mail@email.ru", "mail@email.com", "g@f.ru", "gftr@ghtr.rfg", "abcdeabcdeabcde@abcdeabcdea.abcd"])
    fun `success save client - correct email`(email: String) {
        val client = getClientFromJson("/success/user.json")
        setField(client, "email", email)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @ParameterizedTest(name = "#{index} - email {0} - {2}")
    @MethodSource("incorrectEmailTestArgs")
    fun `save client - incorrect email`(email: String, code: Number, msg: String, value: Int?, constraint: Int?) {
        val client = getClientFromJson("/success/user.json")
        val validatingProperty = "email"
        unsuccessPropertyValidationTest(client, validatingProperty, email, value, code, msg, constraint)
    }

    private fun incorrectEmailTestArgs() : Stream<Arguments> {
        return Stream.of(
            Arguments.of("", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 0, 6),  // too short
            Arguments.of("    ", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 0, 6),  // too short
            Arguments.of("abcd", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 4, 6),  // too short
            Arguments.of("abcdeabcdeabcdeabcdeabcdeabcdeabc", ErrorCode.LENGTH_TOO_LONG.code, ErrorCode.LENGTH_TOO_LONG.msg, 33, 32),  // too long
            Arguments.of("abcdeabcdeabcdeabcdeabcdeabcdeab", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("abcdef", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g@f.рф", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g@F.ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g_f.ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("G@f.ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g@f.Ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g@f_ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("ф@f.ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("Ф@f.ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g@ф.ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g@Ф.ru", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g@f.rШ", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("g@f.rш", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr.r", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr.rfgrh", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr%.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr..rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@gh@tr.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@@ghtr.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gf.tr@ghtr.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@gh.tr.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr.rfg.", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of(".gftr@ghtr.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr.rfg@", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr.r&f", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ght*%r.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ght464r.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gf5345tr@ghtr.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gf$+tr@ghtr.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr.r4g", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@ghtr.123", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("gftr@1234.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
            Arguments.of("1234@ghtr.rfg", ErrorCode.EMAIL_FORMAT.code, ErrorCode.EMAIL_FORMAT.msg, null, null),  // format
        )
    }
    //endregion

    // region phone validation tests
    @ParameterizedTest(name = "#{index} -  snils {0}")
    @MethodSource("correctSnilsTestArgs")
    fun `success save client - correct snils`(snils: String) {
        val client = getClientFromJson("/success/user.json")
        setField(client, "snils", snils)
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    private fun correctSnilsTestArgs() : Stream<Arguments> {
        return Stream.of(
            Arguments.of("56553713311"),
            Arguments.of("11223344595"),
            Arguments.of(generateCorrectSnils("123456789")),
            Arguments.of(generateCorrectSnils("987654321")),
            Arguments.of(generateCorrectSnils("223344556")),
            Arguments.of(generateCorrectSnils("111111111")),
            Arguments.of(generateCorrectSnils("999999999")),
            Arguments.of("00100199800"), // no check for numbers less than "001001998"
            Arguments.of("00100199801"),
            Arguments.of("00100199899"),
            Arguments.of("00100100099"),
        )
    }

    @ParameterizedTest(name = "#{index} - snils {0} - {2}")
    @MethodSource("incorrectSnilsTestArgs")
    fun `save client - incorrect snils`(snils: String, code: Number, msg: String, value: Int?, constraint: Int?) {
        val client = getClientFromJson("/success/user.json")
        val validatingProperty = "snils"
        unsuccessPropertyValidationTest(client, validatingProperty, snils, value, code, msg, constraint)
    }

    private fun incorrectSnilsTestArgs() : Stream<Arguments> {
        return Stream.of(
            Arguments.of("", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 0, 11),  // too short
            Arguments.of("           ", ErrorCode.LENGTH_TOO_SHORT.code, ErrorCode.LENGTH_TOO_SHORT.msg, 0, 11),  // too short
            Arguments.of("1234", ErrorCode.LENGTH_NOT_EQUAL.code, ErrorCode.LENGTH_NOT_EQUAL.msg, 4, 11),  // too short
            Arguments.of("123456789012345", ErrorCode.LENGTH_NOT_EQUAL.code, ErrorCode.LENGTH_NOT_EQUAL.msg, 15, 11),  // too long
            Arguments.of("01234567890", ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of("11223344596", ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of("11223344594", ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of("56553713310", ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of("56553713312", ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of("56553713312", ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of(generateIncorrectSnils("111111111"), ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of(generateIncorrectSnils("123456789"), ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of(generateIncorrectSnils("987654321"), ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of(generateIncorrectSnils("999999999"), ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // 1st number
            Arguments.of(generateIncorrectSnils("001001999"), ErrorCode.SNILS_CONTROL_SUM.code, ErrorCode.SNILS_CONTROL_SUM.msg, null, null),  // min range of check
        )
    }
    //endregion

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")


    private fun unsuccessPropertyValidationTest(
        client: Client, propertyName: String, propertyValue: String, value: Int?, code: Number, msg: String, constraint: Int?
    ) {
        setField(client, propertyName, propertyValue)
        val ex = assertFailsWith<ValidationException>(
            block = { clientService.saveClient(client) }
        )
        assertNotNull(ex)
        assertNotNull(ex.message)

        val expectedMsg = if (value == null) {
            "Constraint Violation! Code: $code - '$msg'"
        } else {
            "Constraint Violation! Code: $code - '$msg'; value: '$value'; constraint: '$constraint'"
        }
        assertTrue(ex.message!!.contains(expectedMsg))
    }
}
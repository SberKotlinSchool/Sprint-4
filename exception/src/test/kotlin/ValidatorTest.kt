import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import kotlin.test.assertEquals

internal class ValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = [
        "Ivan",
        "ИванИванИванИванИванИванИванИван",
    ])
    fun `first name validator test`(value: String) {
        assertEquals(listOf(ErrorCode.INVALID_FIRSTNAME), FirstNameValidator().validate(value))
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "Ivanov",
        "ИвановИвановИвановИвановИвановИвановИвановИванов",
    ])
    fun `last name validator test`(value: String) {
        assertEquals(listOf(ErrorCode.INVALID_LASTNAME), LastNameValidator().validate(value))
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "телефон",
        "89",
        "890571253580000000",
        "56553713311",
    ])
    fun `phone validator test`(value: String) {
        assertEquals(listOf(ErrorCode.INVALID_PHONE), PhoneValidator().validate(value))
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "иванов@mail.ru",
        "ivanovmail.ru",
        "ivanov@mailru",
        "ivanov@mail.",
        "ivanov@mail.a",
        "ivanov@mail.a",
        "i",
        "ivanovivanovivanovivanovivanovivanovivanovivanov@mail.ru",
    ])
    fun `email validator test`(value: String) {
        assertEquals(listOf(ErrorCode.INVALID_EMAIL), EmailValidator().validate(value))
    }

    @ParameterizedTest
    @ValueSource(strings = [
        "снилсиснилс",
        "1",
        "1111111111111111111111",
        "56553713300",
    ])
    fun `snils validator test`(value: String) {
        assertEquals(listOf(ErrorCode.INVALID_SNILS), SnilsValidator().validate(value))
    }
}

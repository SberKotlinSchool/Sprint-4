import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class EmailValidatorTest {
    val emailValidator = EmailValidator()

    private companion object {
        @JvmStatic
        fun emailArguments() = Stream.of(
            Arguments.of(emptyList<ErrorCode>(), "asdsd@ssad.ru")
            , Arguments.of(listOf(ErrorCode.INVALID_EMAIL, ErrorCode.INVALID_EMAIL), "711111111112")
            , Arguments.of(emptyList<ErrorCode>(), "asdssadadasddadasddasdad@ssad.ru")
            , Arguments.of(listOf(ErrorCode.INVALID_EMAIL), "asdssadadakkkkkkkkkksddadasddasdad@ssad.ru")
        )
    }

    @MethodSource("emailArguments")
    @ParameterizedTest
    fun validatePhone(expected: List<ErrorCode>, name: String) {
        assertEquals(expected, emailValidator.validate(name))
    }
}
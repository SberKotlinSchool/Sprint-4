import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class SnilsValidatorTest {
    val snilsValidator = SnilsValidator()

    private companion object {
        @JvmStatic
        fun snilsArguments() = Stream.of(
            Arguments.of(emptyList<ErrorCode>(), "11358337446")
            , Arguments.of(listOf(ErrorCode.INVALID_SNILS, ErrorCode.INVALID_SNILS), "711111111112")
            , Arguments.of(listOf(ErrorCode.INVALID_SNILS), "11358337447")
        )
    }

    @MethodSource("snilsArguments")
    @ParameterizedTest
    fun validatePhone(expected: List<ErrorCode>, name: String) {
        assertEquals(expected, snilsValidator.validate(name))
    }
}
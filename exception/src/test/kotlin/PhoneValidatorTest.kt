import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class PhoneValidatorTest {
    val phoneValidator = PhoneValidator()

    private companion object {
        @JvmStatic
        fun phoneArguments() = Stream.of(
            Arguments.of(emptyList<ErrorCode>(), "71111111111")
            ,Arguments.of(listOf(ErrorCode.INVALID_PHONE_NUMBER), "711111111112")
            ,Arguments.of(listOf(ErrorCode.INVALID_PHONE_NUMBER), "11111111111")
        )
    }

    @MethodSource("phoneArguments")
    @ParameterizedTest
    fun validatePhone(expected: List<ErrorCode>, name: String) {
        assertEquals(expected, phoneValidator.validate(name))
    }
}
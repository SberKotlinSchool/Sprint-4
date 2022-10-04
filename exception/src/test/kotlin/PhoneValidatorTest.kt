import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PhoneValidatorTest {

    @Test
    fun validate() {
        val errorCodeList = PhoneValidator.validate("71234512345")
        assertTrue(errorCodeList.isEmpty())
    }

    companion object {
        @JvmStatic
        fun getValidateErrorsData() = listOf(
            Arguments.of("123qwe"),
            Arguments.of("123 23"),
            Arguments.of("91234512345"),
        )
    }

    @ParameterizedTest
    @MethodSource("getValidateErrorsData")
    fun `validate errors`(str: String) {
        val errorCodeList = PhoneValidator.validate(str)
        assertFalse(errorCodeList.isEmpty())
        assertEquals(ErrorCode.INVALID_PHONE_NUMBER, errorCodeList.get(0))
    }
}
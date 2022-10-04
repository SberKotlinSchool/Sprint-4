import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class SnilsValidatorTest {

    @Test
    fun validate() {
        val errorCodeList = SnilsValidator.validate("88631393650")
        assertTrue(errorCodeList.isEmpty())
    }

    companion object {
        @JvmStatic
        fun getValidateErrorsData() = listOf(
            Arguments.of("886313936501"),
            Arguments.of("as345678901"),
            Arguments.of("88631393649"),
        )
    }

    @ParameterizedTest
    @MethodSource("getValidateErrorsData")
    fun `validate errors`(str: String) {
        val errorCodeList = SnilsValidator.validate(str)
        assertFalse(errorCodeList.isEmpty())
        assertEquals(ErrorCode.INVALID_SNILS, errorCodeList[0])
    }
}
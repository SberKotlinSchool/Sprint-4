import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class NameValidatorTest {

    @Test
    fun validate() {
        val errorCodeList = NameValidator.validate("Иванов")
        assertTrue(errorCodeList.isEmpty())
    }

    companion object {
        @JvmStatic
        fun getValidateErrorsData() = listOf(
            Arguments.of("Ива нов"),
            Arguments.of("Ivanov"),
            Arguments.of("ИванИванИванИванИ"),
        )
    }

    @ParameterizedTest
    @MethodSource("getValidateErrorsData")
    fun `validate errors`(str: String) {
        val errorCodeList = NameValidator.validate(str)
        assertFalse(errorCodeList.isEmpty())
        assertEquals(ErrorCode.INVALID_NAME, errorCodeList.get(0))
    }
}
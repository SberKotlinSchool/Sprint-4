import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class EmailValidatorTest {

    @Test
    fun validate() {
        val errorCodeList = EmailValidator.validate("test1@test1.com")
        assertTrue(errorCodeList.isEmpty())
    }

    companion object {
        @JvmStatic
        fun getValidateErrorsData() = listOf(
            Arguments.of(null),
            Arguments.of("ёest1@test1.com"),
            Arguments.of("ёest1@"),
            Arguments.of("@test1.com"),
            Arguments.of("test1@test112321312312312312312312.com"),
        )
    }

    @ParameterizedTest
    @MethodSource("getValidateErrorsData")
    fun `validate errors`(str: String?) {
        val errorCodeList = EmailValidator.validate(str)
        assertFalse(errorCodeList.isEmpty())
        assertEquals(ErrorCode.INVALID_EMAIL, errorCodeList.get(0))
    }
}
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class PhoneValidatorTest {

    val phoneValidator = PhoneValidator()

    @Test
    fun validate() {
        var errorCodeList = phoneValidator.validate("89992313913")
        assertEquals(0, errorCodeList.size)

        errorCodeList = phoneValidator.validate("79992313913")
        assertEquals(0, errorCodeList.size)

        errorCodeList = phoneValidator.validate("43")
        assertEquals(2, errorCodeList.size)

        errorCodeList = phoneValidator.validate("0")
        assertEquals(2, errorCodeList.size)

        errorCodeList = phoneValidator.validate(null)
        assertEquals(1, errorCodeList.size)
    }
}
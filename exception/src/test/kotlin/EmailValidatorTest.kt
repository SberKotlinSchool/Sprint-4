import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class EmailValidatorTest {

    val emailValidator = EmailValidator()
    @Test
    fun validate() {

        var errorCodeList = emailValidator.validate("kirill@mail.ru")

        //errorCodeList.forEach { (println(it)) }
        assertEquals(0, errorCodeList.size)

        errorCodeList = emailValidator.validate("кирилл@mail.ru")
        assertEquals(1, errorCodeList.size)

    }
}
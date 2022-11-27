abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    private val phoneRegex = Regex("^[78][0-9]{10}\$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!phoneRegex.matches(it)) {
            listOf(ErrorCode.INVALID_PHONE_FORMAT)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.MISSING_PHONE)
}


abstract class NameValidator : Validator<String>() {
    val nameRegex = Regex("^[А-я]{1,16}\$")
}

class FirstNameValidator : NameValidator() {
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!nameRegex.matches(it)) {
            listOf(ErrorCode.INVALID_FIRST_NAME_FORMAT)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.MISSING_FIRST_NAME)
}

class LastNameValidator : NameValidator() {
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!nameRegex.matches(it)) {
            listOf(ErrorCode.INVALID_LAST_NAME_FORMAT)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.MISSING_LAST_NAME)
}


class EmailValidator : Validator<String>() {
    private val emailRegex = Regex("(?=.{0,32}\$)^[A-z]+@[A-z]+\\.[A-z]+\$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!emailRegex.matches(it)) {
            listOf(ErrorCode.INVALID_EMAIL_FORMAT)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.MISSING_EMAIL)
}

class SnilsValidator : Validator<String>() {
    private val snilsRegex = Regex("^[0-9]{11}\$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!snilsRegex.matches(it)) {
            listOf(ErrorCode.INVALID_SNILS_FORMAT)
        } else {
            if (!verifyCheckNumber(it)) {
                listOf(ErrorCode.INVALID_SNILS_CHECKSUM)
            } else {
                emptyList()
            }
        }
    } ?: listOf(ErrorCode.MISSING_SNILS)

    private fun verifyCheckNumber(snils: String): Boolean {
        val snilsWithoutCheckDigit = snils.substring(0, 9)
        val currentChecksum = snils.substring(9).toInt()

        var tempSum = 0
        for (index in snilsWithoutCheckDigit.indices) {
            val position = 9 - index
            tempSum += position * snilsWithoutCheckDigit[index].digitToInt()
        }
        var calculatedChecksum = tempSum % 101
        if (calculatedChecksum == 100) {
            calculatedChecksum = 0
        }
        return currentChecksum == calculatedChecksum
    }
}
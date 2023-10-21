abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

abstract class NameValidator : Validator<String>() {
    val nameRegexPattern = Regex("^[А-я]{1,16}$")
}

class FirstNameValidator : NameValidator() {
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!nameRegexPattern.matches(it)) {
            listOf(ErrorCode.INVALID_FIRST_NAME)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.NO_FIRST_NAME)
}

class LastNameValidator : NameValidator() {
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!nameRegexPattern.matches(it)) {
            listOf(ErrorCode.INVALID_LAST_NAME)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.NO_LAST_NAME)
}


class PhoneValidator : Validator<String>() {
    private val phoneRegexPattern = Regex("^[78][0-9]{10}$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!phoneRegexPattern.matches(it)) {
            listOf(ErrorCode.INVALID_PHONE_NUMBER)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.NO_PHONE_NUMBER)
}

class EmailValidator : Validator<String>() {
    private val emailRegexPattern = Regex("(?=.{0,32}\$)^[A-z]+@[A-z]+\\.[A-z]+\$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!emailRegexPattern.matches(it)) {
            listOf(ErrorCode.INVALID_EMAIL)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.NO_EMAIL)
}

class SnilsValidator : Validator<String>() {
    private val snilsRegexPattern = Regex("^[0-9]{11}\$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!snilsRegexPattern.matches(it)) {
            listOf(ErrorCode.INVALID_SNILS)
        } else {
            if (!checkSnilsNumber(it)) {
                listOf(ErrorCode.INVALID_SNILS_CHECK_DIGIT)
            } else {
                emptyList()
            }
        }
    } ?: listOf(ErrorCode.NO_SNILS)

    private fun checkSnilsNumber(snils: String): Boolean {
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
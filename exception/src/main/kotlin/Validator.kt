abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorCodeList = ArrayList<ErrorCode>()

        requireNotNull(value)
        if (value.length != 11) errorCodeList.add(ErrorCode.INCORRECT_STRING_LENGTH)
        if (!value.startsWith('7') && !value.startsWith('8')) errorCodeList.add(ErrorCode.INVALID_CHARACTER)
        if (!value.all { it.isDigit() }) errorCodeList.add(ErrorCode.DIGIT_ISSUE)

        return errorCodeList
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorCodeList = ArrayList<ErrorCode>()

        requireNotNull(value)

        if (value.length > 16) errorCodeList.add(ErrorCode.VALUE_TOO_BIG)
        if (!value.all {it in 'А'..'я'}) errorCodeList.add(ErrorCode.CYRILLIC_ISSUE)

        return errorCodeList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorCodeList = ArrayList<ErrorCode>()
        val emailRegex = Regex("""^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$""")

        requireNotNull(value)

        if (value.length > 32) errorCodeList.add(ErrorCode.VALUE_TOO_BIG)
        if (!emailRegex.matches(value)) errorCodeList.add(ErrorCode.INVALID_EMAIL)

        return errorCodeList
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorCodeList = ArrayList<ErrorCode>()

        requireNotNull(value)

        if (value.length != 11) errorCodeList.add(ErrorCode.INCORRECT_STRING_LENGTH)
        if (!value.all { it.isDigit() }) errorCodeList.add(ErrorCode.DIGIT_ISSUE)
        if (!validateSnils(value)) errorCodeList.add(ErrorCode.INVALID_SNILS)

        return errorCodeList
    }

    private fun validateSnils(snils: String): Boolean {
        if (snils.length != 11) return false
        val controlSum = snils.substring(9).toInt()
        val digits = snils.substring(0, 9).map { it.toString().toInt() }
        var sum = 0
        for (i in digits.indices) {
            sum += digits[i] * (9 - i)
        }
        val checkSum = when {
            sum < 100 -> sum
            sum in 100..101 -> 0
            else -> sum % 101
        }
        return controlSum == checkSum
    }
}
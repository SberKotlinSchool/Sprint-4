abstract class Validator<T> {
    abstract fun validateField(value: T): List<ErrorCode>

    fun validate(value: T?): List<ErrorCode> {
        if (value == null) {
            return listOf(ErrorCode.NULL_VALUE)
        }
        return validateField(value)
    }
}

class PhoneValidator : Validator<String>() {
    companion object {
        @JvmStatic
        val NUMBER_REGEX = Regex("^[7,8]\\d{10}\$")
    }

    override fun validateField(value: String): List<ErrorCode> {
        val errorCodes = ArrayList<ErrorCode>()

        if (!NUMBER_REGEX.matches(value))
            errorCodes.add(ErrorCode.INVALID_NUMBER)

        return errorCodes
    }
}

class NameValidator : Validator<String>() {
    companion object {
        @JvmStatic
        val NAME_REGEX = Regex("^[А-ЯЁ][а-яё]{0,15}\$")
    }

    override fun validateField(value: String): List<ErrorCode> {
        val errorCodes = ArrayList<ErrorCode>()

        if (!NAME_REGEX.matches(value))
            errorCodes.add(ErrorCode.INVALID_NAME)

        return errorCodes
    }
}

class EmailValidator : Validator<String>() {
    companion object {
        @JvmStatic
        val EMAIL_REGEX = Regex("^[A-Za-z]{1,30}@[A-Za-z]{1,30}\\.[A-Za-z]{1,30}\$")
    }

    override fun validateField(value: String): List<ErrorCode> {
        val errorCodes = ArrayList<ErrorCode>()

        if (value.length > 32 || !EMAIL_REGEX.matches(value))
            errorCodes.add(ErrorCode.INVALID_EMAIL)

        return errorCodes
    }
}

class SnilsValidator : Validator<String>() {
    companion object {
        @JvmStatic
        val SNILS_REGEX = Regex("^\\d{11}\$")
    }

    override fun validateField(value: String): List<ErrorCode> {
        val errorCodes = ArrayList<ErrorCode>()

        if (!SNILS_REGEX.matches(value) || !controlSum(value))
            errorCodes.add(ErrorCode.INVALID_SNILS)

        return errorCodes
    }
}

fun controlSum(value: String): Boolean {
    val snils = value.substring(0, 9)
    val yy = value.substring(9).toInt()
    var sum = 0
    var position = 9
    for (i in 0..8) {
        sum += snils[i].toString().toInt() * position
        position--
    }
    return sum % 101 % 100 == yy

}
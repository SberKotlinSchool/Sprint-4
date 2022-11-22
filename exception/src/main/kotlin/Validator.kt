abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>

    protected fun validateNull(value: String?) = value.isNullOrBlank()
}

class PhoneValidator : Validator<String>() {
    companion object {
        val NUMBER_FORMAT = Regex("^[7,8]\\d{10}$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (validateNull(value) || !NUMBER_FORMAT.matches(value!!))
            return listOf(ErrorCode.INVALID_NUMBER)
        return listOf()
    }
}

class NameValidator : Validator<String>() {
    companion object {
        val NAME_FORMAT = Regex("^[A-я][а-я]{1,15}$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (validateNull(value) || !NAME_FORMAT.matches(value!!))
            return listOf(ErrorCode.INVALID_NAME)
        return listOf()
    }
}

class LastNameValidator : Validator<String>() {
    companion object {
        val LAST_NAME_FORMAT = Regex("^[A-яЁ][а-яё]{1,15}$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (validateNull(value) || !LAST_NAME_FORMAT.matches(value!!))
            return listOf(ErrorCode.INVALID_LAST_NAME)
        return listOf()
    }
}

class EmailValidator : Validator<String>() {
    companion object {
        val EMAIL_FORMAT = Regex("^[A-Za-z]{1,30}@[A-Za-z]{1,30}\\.[a-z]{1,30}$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (validateNull(value) || !EMAIL_FORMAT.matches(value!!) || value.length > 32)
            return listOf(ErrorCode.INVALID_EMAIL)

        return listOf()
    }
}

class SNILSValidator : Validator<String>() {
    companion object {
        val SNILS_FORMAT = Regex("^\\d{11}\$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (validateNull(value) || !SNILS_FORMAT.matches(value!!) || !checkControlSum(value))
            return listOf(ErrorCode.INVALID_SNILS)
        return listOf()
    }

    private fun checkControlSum(value: String): Boolean {
        val snils = value.substring(0, 9)
        val yy = value.substring(9).toInt()
        var sum = 0
        for (i in 0..8) {
            sum += snils[i].toString().toInt()*(9-i)
        }
        return sum % 101 % 100 == yy
    }
}


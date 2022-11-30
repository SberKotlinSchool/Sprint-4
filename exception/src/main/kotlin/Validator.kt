abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>

    protected fun validateString(value: String?, errorCode: ErrorCode, regex: Regex): List<ErrorCode> {
        if (value.isNullOrBlank()) return listOf(ErrorCode.STRING_NULL_OR_BLANK)
        if (!value.matches(regex)) return listOf(errorCode)
        return emptyList()
    }
}

class PhoneValidator : Validator<String>() {

    private val regex = Regex("^(7|8)?[0-9]{10}$")

    override fun validate(value: String?): List<ErrorCode> {
        return validateString(value, ErrorCode.INVALID_FORMAT_PHONE, regex)
    }
}

class NameValidator : Validator<String>() {

    private val regex = Regex("[А-Яа-я]{1,16}")

    override fun validate(value: String?): List<ErrorCode> {
        return validateString(value, ErrorCode.INVALID_FORMAT_NAME, regex)
    }
}

class EmailValidator : Validator<String>() {

    //не получилось указать длину в {5,32} символа
    private val regex = Regex("[a-zA-Z0-9]+@[a-z]+\\.[a-z]+")

    override fun validate(value: String?): List<ErrorCode> {
        val errors = validateString(value, ErrorCode.INVALID_FORMAT_EMAIL, regex)
        if (errors.isNotEmpty()) return errors
        if (value!!.length > 32) return listOf(ErrorCode.INVALID_FORMAT_EMAIL)
        return emptyList()
    }
}

class SNILSValidator : Validator<String>() {

    private val regex = Regex("([0-9]{11})")

    override fun validate(value: String?): List<ErrorCode> {
        val errors = validateString(value, ErrorCode.INVALID_FORMAT_SNILS, regex)
        if (errors.isNotEmpty()) return errors
        val errorCodeSum = ErrorCode.INVALID_FORMAT_SNILS_SUM
        value?.let {
            var sum = 0
            val controlSum = it.substring(9, 11).toInt()
            for (i in 0..8)
                sum += it[i].digitToInt() * (9 - i)
            if (sum < 100) {
                if (sum != controlSum) return listOf(errorCodeSum)
            }
            if (sum == 100 && controlSum != 0) {
                return listOf(errorCodeSum)
            }
            if (sum > 100) {
                val div = sum % 101
                if (div == 100) {
                    if (controlSum != 0) return listOf(errorCodeSum)
                } else {
                    if (controlSum != div) return listOf(errorCodeSum)
                }
            }
        }
        return emptyList()
    }
}
import mu.KLogging

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>

    companion object : KLogging()
}

open class StringRegExpValidator(
    private val regExp: String, private val errorCode: ErrorCode
) : Validator<String?>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.INVALID_NULL_OR_EMPTY)
        if (!value.lowercase().matches(regExp.toRegex())) {
            logger.info { "строка: '$value' не соответствует regExp:'$regExp'" }
            return listOf(errorCode)
        }
        return emptyList()
    }
}

class PhoneValidator : StringRegExpValidator("([78])\\d{10}", ErrorCode.INVALID_PHONE)
class NameValidator : StringRegExpValidator("^[а-я]{0,16}$", ErrorCode.INVALID_NAME)

class EmailValidator : StringRegExpValidator("^[a-z]+@[a-z]+.[a-z]{0,3}$", ErrorCode.INVALID_EMAIL) {
    override fun validate(value: String?): List<ErrorCode> {
        val error = super.validate(value)
        if (error.isEmpty() && value!!.length > 33) {
            logger.info { "длина строки email: '$value' = ${value.length} больше 33 символов" }
            return listOf(ErrorCode.INVALID_EMAIL)
        }
        return error
    }
}

class SnilsValidator : StringRegExpValidator("^([0-9]){11}$", ErrorCode.INVALID_SNILS) {
    override fun validate(value: String?): List<ErrorCode> {
        val error = super.validate(value)
        if (error.isEmpty() && !isValidSumCode(value!!)) {
            return listOf(ErrorCode.INVALID_SNILS)
        }
        return error
    }

    private fun isValidSumCode(value: String): Boolean {
        var sum = 0
        for (i in 0 until 9) {
            val number = value[i].toString().toInt()
            sum += number * (9 - i)
        }
        var check = 0
        if (sum < 100) {
            check = sum
        } else if (sum > 101) {
            check = (sum % 101)
            if (check == 100) {
                check = 0
            }
        }
        val controlSum = value.substring(9, 11).toInt()
        logger.info { "СНИЛС: $value, контрольная суммма: $controlSum, рассчитанная: $check" }
        return check == controlSum
    }
}

open class Validator<T>(private val regex: String,
                        private val error: ErrorCode) {
    open fun validate(value: String?): List<ErrorCode> {
        val errors = ArrayList<ErrorCode>()

        value?.let {
            if (!it.matches(Regex(regex)))
                errors.add(error) }
            ?: errors.add(ErrorCode.EMPTY_INPUT)

        return errors
    }
}

class FirstNameValidator : Validator<String>(regex = "^[\\p{IsCyrillic}]{1,16}\$",
                                        error = ErrorCode.INVALID_FIRSTNAME)

class LastNameValidator : Validator<String>(regex = "^[\\p{IsCyrillic}]{1,16}\$",
                                           error = ErrorCode.INVALID_LASTNAME)

class PhoneValidator : Validator<String>(regex = "^[7|8]\\d{10}\$",
                                         error = ErrorCode.INVALID_PHONE)

class EmailValidator : Validator<String>(regex = "^(?=.{6,32}\$)\\w+@[a-z_]+\\.[a-z]{2,6}\$",
                                         error = ErrorCode.INVALID_EMAIL)

class SnilsValidator : Validator<String>(regex = "^\\d{11}\$",
                                         error = ErrorCode.INVALID_SNILS) {
    override fun validate(value: String?): List<ErrorCode> {
        return super.validate(value).ifEmpty { validateCheckNumber(value!!) }
    }

    private fun validateCheckNumber(value: String) : List<ErrorCode> {
        val errors = ArrayList<ErrorCode>()
        val mainNumber = value.substring(0, 9)
        if (mainNumber.toInt() > 1001998) {
            val digits = mainNumber.map { it.digitToInt() }.reversed()
            var sum = 0
            digits.forEachIndexed { index, i ->
                sum += i * (index+1)
            }
            if (value.substring(9).toInt() !=  sum % 101) {
                errors.add(ErrorCode.INVALID_SNILS)
            }
        }

        return errors
    }
}
abstract class Validator<T> {

    abstract fun validate(value: T?): List<ErrorCode>

}

class PhoneValidator : Validator<String>() {

    private val template = "^(7|8)\\d{10}$"

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty() || !value.matches(Regex(template)) )
            return listOf(ErrorCode.INVALID_PHONE_NUMBER)
        return emptyList()
    }
}

class EmailValidator : Validator<String>() {

    private var template = "^(?=.{1,32}$)[A-Za-z]+@[A-Za-z]+\\.([A-Za-z]{2,3})$"

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty() || !value.matches(Regex(template)) )
            return listOf(ErrorCode.INVALID_EMAIL)
        return emptyList()
    }
}

class SnilsValidator : Validator<String>() {

    private val template = "^\\d{11}$"
    override fun validate(value: String?): List<ErrorCode> {
        val errors = ArrayList<ErrorCode>();
        if (value.isNullOrEmpty() || !value.matches(Regex(template)) )
            errors.add(ErrorCode.INVALID_SNILS)
        if (!calulateCheckSum(value!!).equals(value.substring(9, 11)))
            errors.add(ErrorCode.INVALID_SNILS_CHECK_SUM)
        return errors
    }

    private fun calulateCheckSum(value: String): String
    {
        val sum = value.substring(0, 9).map { it.digitToInt() }
            .reversed().reduceIndexed { index, acc, it -> acc + it * (index + 1) }
        when {
               sum < 100 -> return sum.toString().padStart(2, '0')
               sum == 100 -> return "00"
               sum == 101 -> return "00"
               sum > 101 -> return (sum % 101).toString().padStart(2, '0')
        }
        return ""
    }
}

class NameValidator : Validator<String>() {

    private var template = "^[А-Яа-я]{1,16}$"
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty() || !value.matches(Regex(template)) )
            return listOf(ErrorCode.INVALID_NAME)
        return emptyList()
    }
}

class SurnameValidator : Validator<String>() {

    private var template = "^[А-Яа-я]{1,16}$"

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty() || !value.matches(Regex(template)) )
            return listOf(ErrorCode.INVALID_NAME)
        return emptyList()
    }
}

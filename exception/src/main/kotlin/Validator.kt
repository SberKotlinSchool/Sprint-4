abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    private val errorList = ArrayList<ErrorCode>()
    override fun validate(value: String?): List<ErrorCode> {
        value?.let { str ->
            if (str[0] !in '7'..'8' || !str.all { it.isDigit() }) errorList.add(ErrorCode.INVALID_CHARACTER_PHONE)
            if (str.length != 11) errorList.add(ErrorCode.INVALID_LENGTH_PHONE)
        }
        return errorList
    }
}

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?) =
            validateName(value, ErrorCode.INVALID_LENGTH_FIRST_NAME, ErrorCode.INVALID_CHARACTER_FIRST_NAME)
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?) =
            validateName(value, ErrorCode.INVALID_LENGTH_LAST_NAME, ErrorCode.INVALID_CHARACTER_LAST_NAME)
}

class EmailValidator : Validator<String>() {
    private val errorList = ArrayList<ErrorCode>()
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$"
    override fun validate(value: String?): List<ErrorCode> {
        value?.let {
            if (!it.matches(emailRegex.toRegex())) errorList.add(ErrorCode.INVALID_CHARACTER_EMAIL)
            if (it.length > 32) errorList.add(ErrorCode.INVALID_LENGTH_EMAIL)
        }
        return errorList
    }
}


class SnilsValidator : Validator<String>() {
    private val errorList = ArrayList<ErrorCode>()

    override fun validate(value: String?): List<ErrorCode> {
        value?.let {str ->
            if(!str.all { it.isDigit() }) {
                return listOf(ErrorCode.INVALID_CHARACTER_SNILS)
            }
            if (str.length != 11) return listOf(ErrorCode.INVALID_LENGTH_SNILS)
            if (value.substring(0,9).toInt() > 1001998) {
                var position = 9
                val sum = str.substring(0,9).toCharArray().map { it.digitToInt() * position-- }.reduce{ a, b -> a + b }
                val checkNumber = when (sum) {
                    99 -> "99"
                    100, 101 -> "00"
                    102 -> "01"
                    else -> (sum % 101).toString().let {
                        when (it.length) {
                            1 -> "0$it"
                            2 -> it
                            else -> it.substring(str.length - 2)
                        }
                    }
                }
                if (checkNumber != str.substring(str.length-2)) errorList.add(ErrorCode.INVALID_CHECK_NUMBER_SNILS)
            }
        }
        return errorList
    }
}

private fun validateName(value: String?, errorCodeLength: ErrorCode, errorCodeCharacter: ErrorCode, ): List<ErrorCode> {
    val errorList = ArrayList<ErrorCode>()
    val nameRegex = "^[А-Яа-я]+\$"
    value?.let {str ->
        if (str.length > 16) errorList.add(errorCodeLength)
        if (!str.matches(nameRegex.toRegex())) errorList.add(errorCodeCharacter)
    } ?: errorList.add(errorCodeLength)
    return errorList
}
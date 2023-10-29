import ErrorCode.*

abstract class Validator<T> {
    val errors: ArrayList<ErrorCode> = ArrayList()

    abstract fun checkErrors(value: T)

    fun validate(value: T?): List<ErrorCode> {
        if (value == null) {
            errors.add(VALUE_IS_NULL)
        } else {
            checkErrors(value)
        }
        return errors
    }
}

class PhoneValidator : Validator<String>() {
    override fun checkErrors(value: String) {
        if (!checkFirstNumbers(value.substring(0, 2))) {
            errors.add(INVALID_REGION)
        }
        if (value.length != 11) {
            errors.add(INVALID_PHONE_LENGTH)
        }
        var check = false
        for (i in value.indices) {
            check = i == 0 && value[i] != '+' || i > 0 && value[i].isDigit()
        }
        if (!check) {
            errors.add(INVALID_PHONE_NUMS)
        }
    }

    private fun checkFirstNumbers(value: String): Boolean {
        return if (value.startsWith('8')) {
            true
        } else if (value == "+7") {
            true
        } else {
            false
        }
    }
}

class NameValidator : Validator<String>() {
    override fun checkErrors(value: String) {
        if (value.length > 16) {
            errors.add(INVALID_NAME_LENGTH)
        }
        var check = false
        for (char in value) {
            check = char.code in 1072..1103 || char.code in 1140..1171
        }
        if (!check) {
            errors.add(INVALID_NAME_LANGUAGE)
        }

    }
}

class EmailValidator : Validator<String>() {
    override fun checkErrors(value: String) {
        if (value.length > 32) {
            errors.add(INVALID_EMAIL_LENGTH)
        }
        if (!checkLanguage(value)) {
            errors.add(INVALID_EMAIL_LANGUAGE)
        }
        if (!Regex("[A-Za-z]+@[A-Za-z]+.+.[A-Za-z]{2,4}").matches(value)) {
            errors.add(INVALID_EMAIL_DOMEN)
        }
    }

    private fun checkLanguage(value: String): Boolean {
        var result = false
        for (char in value) {
            result = char.code in 64..90 || char.code in 97..122 || char.code == 46
            if (!result) {
                break
            }
        }
        return result
    }
}

class SnilsValidator : Validator<String>() {
    override fun checkErrors(value: String) {
        if (value.length != 11) {
            errors.add(INVALID_SNILS_LENGTH)
        }
        if (!Regex("[0-9]{11}").matches(value)) {
            errors.add(INVALID_SNILS_CHARACTER)
        } else if (!validateControlNumber(value)) {
            errors.add(INVALID_SNILS_CONTROL_NUMBER)
        }

    }

    private fun validateControlNumber(value: String): Boolean {
        var number = value.substring(0, 9).toInt()
        val controlNumber = value.substring(9, 11).toInt()
        var expected = 0
        var index = 1
        while (number != 0) {
            val tmp = number % 10
            expected += tmp * index++
            number /= 10
        }
        return controlNumber == expected % 101
    }
}
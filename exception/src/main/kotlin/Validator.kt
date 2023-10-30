const val MAX_NAME_LENGTH = 16
const val PHONE_LENGTH = 11
const val MAX_EMAIL_LENGTH = 32
const val SNILS_LENGTH = 11

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        if (value != null) {
            if (!value.matches("^[а-яА-Я]*$".toRegex())) {
                errorList.add(ErrorCode.INVALID_CHARACTER)
            }
            if (value.length > MAX_NAME_LENGTH) {
                errorList.add(ErrorCode.INVALID_NAME_LENGTH)
            }
        } else {
            errorList.add(ErrorCode.PARAMETER_IS_NULL)
        }
        return errorList
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        if (value != null) {
            if (!(value.startsWith('7') || value.startsWith('8'))) {
                errorList.add(ErrorCode.INVALID_PHONE_PREFIX)
            }
            if (!value.matches("^[0-9]*$".toRegex())) {
                errorList.add(ErrorCode.INVALID_CHARACTER)
            }
            if (value.length != PHONE_LENGTH) {
                errorList.add(ErrorCode.INVALID_PHONE_LENGTH)
            }
        } else {
            errorList.add(ErrorCode.PARAMETER_IS_NULL)
        }
        return errorList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        if (value != null) {
            if (value.length > MAX_EMAIL_LENGTH) {
                errorList.add(ErrorCode.INVALID_EMAIL_LENGTH)
            }
            if (!value.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex())) {
                errorList.add(ErrorCode.INVALID_EMAIL_DOMAIN)
            }
        } else {
            errorList.add(ErrorCode.PARAMETER_IS_NULL)
        }
        return errorList
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        if (value != null) {
            if (value.length != SNILS_LENGTH) {
                errorList.add(ErrorCode.INVALID_SNILS_LENGTH)
            }
        } else {
            errorList.add(ErrorCode.PARAMETER_IS_NULL)
        }
        return errorList
    }
}
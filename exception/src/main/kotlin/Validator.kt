abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    private val phoneValidatorSettings = Regex("^[78][0-9]{10}$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!phoneValidatorSettings.matches(it)) {
            listOf(ErrorCode.INVALID_PHONE_NUMBER)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.NO_PHONE)
}


class FirstNameValidator : Validator<String>() {
    private val firstNameValidatorSettings = Regex("^[А-я]{2,16}$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!firstNameValidatorSettings.matches(it)) {
            listOf(ErrorCode.INVALID_FIRST_NAME)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.NO_FIRST_NAME)
}

class LastNameValidator : Validator<String>() {
    private val lastNameValidatorSettings = Regex("^[А-я]{2,16}$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!lastNameValidatorSettings.matches(it)) {
            listOf(ErrorCode.INVALID_LAST_NAME)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.NO_LAST_NAME)
}

class SnilsValidator : Validator<String>() {
    private val snilsValidatiorSettings = Regex("^[0-9]{11}\$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!snilsValidatiorSettings.matches(it)) {
            listOf(ErrorCode.INVALID_SNILS)
        } else {
                emptyList()
            }
        } ?: listOf(ErrorCode.NO_SNILS)
}

class EmailValidator : Validator<String>() {
    private val emailValidatorSettings = Regex("(?=.{0,32}\$)^[A-z]+@[A-z]+\\.[A-z]+\$")
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        if (!emailValidatorSettings.matches(it)) {
            listOf(ErrorCode.INVALID_EMAIL)
        } else {
            emptyList()
        }
    } ?: listOf(ErrorCode.NO_EMAIL)
}

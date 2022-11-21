abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

val namePattern = "[а-яА-ЯёЁ]{1,16}".toRegex()
val phonePattern = "^[7|8]+[0-9]{10}$".toRegex()
val emailPattern = "(?=.{5,32}\$)[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,4}$".toRegex()
val snilsPattern = "^[0-9]{11}$".toRegex()

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value.isNullOrBlank()) listOf(ErrorCode.LAST_NAME_IS_EMPTY)
        else if (!value.matches(namePattern)) listOf(ErrorCode.LAST_NAME_INVALID_CHARACTER)
        else listOf()
}

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value.isNullOrBlank()) listOf(ErrorCode.FIRST_NAME_IS_EMPTY)
        else if (!value.matches(namePattern)) listOf(ErrorCode.FIRST_NAME_INVALID_CHARACTER)
        else listOf()
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value?.matches(phonePattern) != true) listOf(ErrorCode.INVALID_PHONE_NUMBER)
        else listOf()
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value?.matches(emailPattern) != true) listOf(ErrorCode.INVALID_EMAIL)
        else listOf()
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value?.matches(snilsPattern) != true) listOf(ErrorCode.INVALID_SNILS)
        else listOf()
}

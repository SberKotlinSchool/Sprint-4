abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?) = when {
        value?.matches(Regex("^[7|8]+[0-9]{10}$")) == true -> listOf()
        else -> listOf(ErrorCode.INVALID_PHONE)
    }
}

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?) = when {
        value?.matches(Regex("[а-яА-ЯёЁ]{1,16}")) == true -> listOf()
        else -> listOf(ErrorCode.INVALID_FIRST_NAME)
    }
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?) = when {
        value?.matches(Regex("[а-яА-ЯёЁ]{1,16}")) == true -> listOf()
        else -> listOf(ErrorCode.INVALID_LAST_NAME)
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?) = when {
        value?.matches(Regex("(?=.{5,32}\$)[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,4}$")) == true -> listOf()
        else -> listOf(ErrorCode.INVALID_EMAIL)
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?) = when {
        value?.matches(Regex("^[0-9]{11}$")) == true -> listOf()
        else -> listOf(ErrorCode.INVALID_SNILS)
    }
}
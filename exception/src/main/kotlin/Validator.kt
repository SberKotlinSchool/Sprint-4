abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>

    companion object{
        val LAT = "[a-zA-Z]"
        val CYR = "[а-яА-Я]"
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value.isNullOrEmpty() or (value?.matches(Regex("[78][0-9]{10}")) == true))
            listOf()
        else
            listOf(ErrorCode.INVALID_PHONE)
}

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value.isNullOrEmpty() or (value?.matches(Regex("$CYR{1,16}")) == true))
            listOf()
        else
            listOf(ErrorCode.INVALID_FIRST_NAME)
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value.isNullOrEmpty() or (value?.matches(Regex("$CYR{1,16}")) == true))
            listOf()
        else
            listOf(ErrorCode.INVALID_LAST_NAME)
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (
            value.isNullOrEmpty() or
            ((value?.matches(Regex("$LAT+@$LAT+(\\.$LAT+)+")) == true) and (value?.length!! < 16))
        )
            listOf()
        else
            listOf(ErrorCode.INVALID_EMAIL)
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?) =
        if (value.isNullOrEmpty() or (value?.matches(Regex("[0-9]{11}")) == true))
            listOf()
        else
            listOf(ErrorCode.INVALID_SNILS)
}
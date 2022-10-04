abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

/**
 * **Имя и Фамилия** - только кириллица, не более 16 символов каждое поле <br>
 */
object NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[ёЁА-я]{1,16}".toRegex()
        if (value?.matches(regex) != true) {
            return listOf(ErrorCode.INVALID_NAME)
        }
        return emptyList()
    }
}

/**
 * **Телефон** - начинается с 7 или 8ки, только цифры, 11 знаков <br>
 */
object PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[7,8]\\d{10}".toRegex()
        if (value?.matches(regex) != true) {
            return listOf(ErrorCode.INVALID_PHONE_NUMBER)
        }
        return emptyList()
    }
}

/**
 * **Email** - латиница, с валидацией @имя_домена, не более 32 символов <br>
 */
object EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "\\w+@(\\w+\\.)*\\w+".toRegex()
        if ((value?.length ?: 0) > 32 || value?.matches(regex) != true) {
            return listOf(ErrorCode.INVALID_EMAIL)
        }
        return emptyList()
    }
}

/**
 * **СНИЛС** - только цифры, 11 символов, с валидацией Контрольного числа <br>
 */
object SnilsValidator : Validator<String> () {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "\\d{11}".toRegex()
        if (value?.matches(regex) != true) {
            return listOf(ErrorCode.INVALID_SNILS)
        }
        return emptyList()
    }
}
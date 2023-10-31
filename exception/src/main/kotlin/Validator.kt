import kotlin.math.E

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class LastNameFirstNameValidator : Validator<String>() {
    override fun validate(value: String?) = ArrayList<ErrorCode>().apply {
        if (value == null) add(ErrorCode.FIELD_IS_EMPTY) else {
            if (!value.matches("([А-ЯЁ][а-яё]+[\\-\\s]?)".toRegex())) add(ErrorCode.INVALID_CHARACTER)
            if (value.length > 16) add(ErrorCode.INVALID_CHARACTER_COUNT)
        }
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?) = ArrayList<ErrorCode>().apply {
        if (value == null) add(ErrorCode.FIELD_IS_EMPTY) else {
            if (!value.matches("^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}\$".toRegex())) add(ErrorCode.INVALID_PHONE_FORMAT)
        }
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?) = ArrayList<ErrorCode>().also {
        if (value == null) it.add(ErrorCode.FIELD_IS_EMPTY) else {
            if (!value.matches("([a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+)".toRegex())) it.add(ErrorCode.INVALID_EMAIL_FORMAT)
        }
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> = ArrayList<ErrorCode>().also {
        if (value == null) it.add(ErrorCode.FIELD_IS_EMPTY) else {
            if (!isValid(value)) it.add(ErrorCode.INVALID_CONTROL_NUMBER)
        }
    }

    fun isValid(value: String?) =
        value?.let {
            val lastTwo = value
                .takeIf { value.length == 11 || it.matches("^\\d+\$".toRegex()) }
                ?.takeLast(2)
                ?.toInt()
                ?: return false
            val digits = value
                .dropLast(2)
                .takeIf { it.toInt() > 1_001_998 }
                ?.chunked(1)
                ?.map { it.toInt() }
                ?.mapIndexed { i, num -> num * (value.length - 2 - i) }
                ?.sum()
                ?: return false

            return if (digits < 100) {
                digits == lastTwo
            } else {
                val result = (digits % 101).takeIf { it != 100 } ?: 0
                lastTwo == result
            }
        }
            ?: true
}
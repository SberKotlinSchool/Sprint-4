import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errors = ArrayList<ErrorCode>();
        if (value.isNullOrEmpty())
            errors.add(ErrorCode.EMPTY_CONTENT)
        else {
            errors.addAll(checkStringLength(value, 11, 11))
            if (!value!!.contains("^[7-8]([0-9]+)$".toRegex()))
                errors.add(ErrorCode.INVALID_CHARACTER)
        }
        return errors
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errors = ArrayList<ErrorCode>()
        if (value.isNullOrEmpty())
            errors.add(ErrorCode.EMPTY_CONTENT)
        else {
            errors.addAll(checkStringLength(value, 0, 16))
            if (!Pattern.matches(".*\\p{InCyrillic}.*", value))
                errors.add(ErrorCode.INVALID_CHARACTER)
        }
        return errors
    }
}

fun checkStringLength(value: String, minLength: Int, maxLength: Int ) : List<ErrorCode> {
    val errors = ArrayList<ErrorCode>()
    if (value.length < minLength || value.length > maxLength)
        errors.add(ErrorCode.INVALID_LENGTH)
    return errors
}
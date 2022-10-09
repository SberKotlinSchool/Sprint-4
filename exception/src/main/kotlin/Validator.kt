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
            if (!Pattern.matches("[\\p{IsCyrillic}]*", value))
                errors.add(ErrorCode.INVALID_CHARACTER)
        }
        return errors
    }
}

class SnilsValidator: Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errors = ArrayList<ErrorCode>()
        if (value.isNullOrEmpty())
            errors.add(ErrorCode.EMPTY_CONTENT)
        else {
            errors.addAll(checkStringLength(value, 11, 11))
            if (!value!!.matches("\\d+".toRegex()))
                errors.add(ErrorCode.INVALID_CHARACTER)
            errors.addAll(validateSnils(value))
        }
        return errors
    }

    private fun validateSnils(value: String): List<ErrorCode> {
        if (value.substring(0, 9) <= "001001998") {
            return emptyList()
        }
        val valueLength = value.length - 2
        var sumForCheck = 0
        for (i in 0..8) {
            sumForCheck += value.substring(i, i + 1).toInt() * (valueLength - i)
        }
        if (sumForCheck > 101) {
            sumForCheck %= 101
        }
        if (sumForCheck == 100 || sumForCheck == 101) {
            sumForCheck = 0
        }
        return if (sumForCheck == value.substring(9, 11).toInt()) emptyList() else listOf(ErrorCode.INVALID_SNILS);
    }
}

class EmailValidator: Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errors = ArrayList<ErrorCode>()
        if (value.isNullOrEmpty())
            errors.add(ErrorCode.EMPTY_CONTENT)
        else {
            val pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
            if (!value!!.matches(pattern.toRegex()))
                errors.add(ErrorCode.INVALID_EMAIL)
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
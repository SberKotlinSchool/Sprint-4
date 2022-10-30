import java.util.regex.Matcher
import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

open class CommonValidator(private val maxLength: Int = 0) : Validator<String>() {
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = mutableListOf<ErrorCode>()
        if (value === null) {
            errors.add(ErrorCode.EMPTY_VALUE)
        } else {
            if (this.maxLength != 0 && value.length > this.maxLength) {
                errors.add(ErrorCode.MAX_LENGTH)
            }
        }
        return errors
    }
}

open class NumberValidator(private val maxLength: Int = 0) : Validator<String>() {
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = mutableListOf<ErrorCode>()
        if (value === null) {
            errors.add(ErrorCode.EMPTY_VALUE)
        } else {
            if (value.contains(Regex("[^0-9]"))) {
                errors.add(ErrorCode.NUM_ONLY)
            }
            if (this.maxLength != 0 && value.length != this.maxLength) {
                errors.add(ErrorCode.NUMBER_LENGTH)
            }
        }
        return errors
    }
}

class PhoneValidator : NumberValidator(11) {
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        if (errors.isEmpty()) {
            value?.let {
                if (!it.contains(Regex("^[78]"))) {
                    errors.add(ErrorCode.PHONE_NOT_MATCH)
                }
            }
        }
        return errors
    }
}

open class FirstNameValidator : CommonValidator(16) {
    private val namePattern: Pattern = Pattern.compile("/[^а-яА-Я]/")
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        value?.let {
            val nameMatcher: Matcher = namePattern.matcher(value)
            if (nameMatcher.matches()) {
                errors.add(ErrorCode.INVALID_CHARACTER)
            }
        }
        return errors
    }
}

class LastNameValidator : FirstNameValidator() {
    override fun validate(value: String?): MutableList<ErrorCode> {
        return super.validate(value)
    }
}

class EmailValidator : CommonValidator(32) {
    private val emailPattern: Pattern = Pattern.compile(".+@.+\\.[a-z]+")
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        value?.let {
            val emailMatcher: Matcher = emailPattern.matcher(value)
            if (!emailMatcher.matches()) {
                errors.add(ErrorCode.EMAIL_NOT_MATCH)
            }
        }

        return errors
    }
}

class SnilsValidator : NumberValidator(11) {
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        value?.let {
            if (!errors.contains(ErrorCode.NUM_ONLY)) {
                val num = it.toLong()
                if (num < 1001998) {
                    errors.add(ErrorCode.CONTROL_VALUE)
                }
            }
        }

        return errors
    }
}

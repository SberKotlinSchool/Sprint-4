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
    private val pattern = "[^0-9]".toRegex()
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = mutableListOf<ErrorCode>()
        if (value === null) {
            errors.add(ErrorCode.EMPTY_VALUE)
        } else {
            if (value.contains(pattern)) {
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
                if (!(it[0] == '7' || it[0] == '8')) {
                    errors.add(ErrorCode.PHONE_NOT_MATCH)
                }
            }
        }
        return errors
    }
}

open class FirstNameValidator : CommonValidator(16) {
    private val namePattern = "[^а-яА-Я]".toRegex()
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        value?.let {
            if (value.matches(namePattern)) {
                errors.add(ErrorCode.INVALID_CHARACTER)
            }
        }
        return errors
    }
}

class EmailValidator : CommonValidator(32) {
    private val emailPattern = ".+@.+\\.[a-z]+".toRegex()
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        value?.let {
            if (!value.matches(emailPattern)) {
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

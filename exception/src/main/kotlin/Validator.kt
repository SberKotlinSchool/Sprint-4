abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator(
    private val emptyStringValidator: Validator<String>,
    private val fixLengthStringValidatorUtil: FixLengthStringValidatorUtil,
    private val onlyDigitStringValidatorUtil: OnlyDigitStringValidatorUtil
) : Validator<String>() {
    private companion object {
        private const val REQUIRED_LENGTH = 11
        private val START_WITH_REGEX = "^([87]).*".toRegex()
    }

    override fun validate(value: String?): List<ErrorCode> {
        val result = value.validate(emptyStringValidator)
        if (result.isNotEmpty()) {
            return result
        }
        fixLengthStringValidatorUtil.validateStringFixLength(value!!, REQUIRED_LENGTH, result)
        onlyDigitStringValidatorUtil.validateStringOnlyNumber(value, result)
        if (!START_WITH_REGEX.matches(value)) {
            result.add(ErrorCode.INVALID_PHONE_CODE)
        }
        return result
    }
}

class ProperNameValidator(
    private val emptyStringValidator: Validator<String>,
    private val maxLengthStringValidatorUtil: MaxLengthStringValidatorUtil
) : Validator<String>() {
    private companion object {
        private const val MAX_LENGTH = 16
        private val CYRILLIC_REGEX = "^[а-яА-Я]+\$".toRegex()
    }

    override fun validate(value: String?): List<ErrorCode> {
        val result = value.validate(emptyStringValidator)
        if (result.isNotEmpty()) {
            return result
        }
        maxLengthStringValidatorUtil.validateStringMaxLength(value!!, MAX_LENGTH, result)
        if (!CYRILLIC_REGEX.matches(value)) {
            result.add(ErrorCode.INVALID_CHARACTER)
        }
        return result
    }
}

class EmailValidator(
    private val emptyStringValidator: Validator<String>,
    private val maxLengthStringValidatorUtil: MaxLengthStringValidatorUtil
) : Validator<String>() {
    private companion object {
        private const val MAX_LENGTH = 32
        private val EMAIL_SYMBOLS_REGEX = "^([a-zA-Z]|@|\\.)+\$".toRegex()
        private val SIMPLE_EMAIL_FORMAT_REGEX = "^(\\S+)(@)(\\S+)(\\.)(\\S+)\$".toRegex()
    }

    override fun validate(value: String?): List<ErrorCode> {
        val result = value.validate(emptyStringValidator)
        if (result.isNotEmpty()) {
            return result
        }
        maxLengthStringValidatorUtil.validateStringMaxLength(value!!, MAX_LENGTH, result)
        if (!EMAIL_SYMBOLS_REGEX.matches(value)) {
            result.add(ErrorCode.INVALID_CHARACTER)
        }
        if (!SIMPLE_EMAIL_FORMAT_REGEX.matches(value)) {
            result.add(ErrorCode.INCORRECT_EMAIL_FORMAT)
        }
        return result
    }
}

class SnilsValidator(
    private val emptyStringValidator: Validator<String>,
    private val onlyDigitStringValidatorUtil: OnlyDigitStringValidatorUtil,
    private val fixLengthStringValidatorUtil: FixLengthStringValidatorUtil
) : Validator<String>() {
    private companion object {
        private const val REQUIRED_LENGTH = 11
    }

    override fun validate(value: String?): List<ErrorCode> {
        val result = value.validate(emptyStringValidator)
        if (result.isNotEmpty()) {
            return result
        }
        onlyDigitStringValidatorUtil.validateStringOnlyNumber(value!!, result)
        fixLengthStringValidatorUtil.validateStringFixLength(value, REQUIRED_LENGTH, result)
        if (result.isEmpty() && !validateControlNumber(value)) {
            result.add(ErrorCode.INVALID_SNILS_CONTROL_NUMBER)
        }
        return result
    }

    private fun validateControlNumber(value: String): Boolean {
        val controlNumber = value.last().digitToInt() + value[value.length - 2].digitToInt() * 10
        var controlSum = 0
        for (i in 0..value.length - 3) {
            controlSum += value[i].digitToInt()  * (value.length - 2 - i)
        }
        return controlSum % 101 == controlNumber
    }
}

class EmptyStringValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()
        if (value == null) {
            result.add(ErrorCode.NULL_VALUE)
            return result
        }
        if (value.trim().isEmpty()) {
            result.add(ErrorCode.EMPTY_VALUE)
        }
        return result
    }
}

class MaxLengthStringValidatorUtil {
    fun validateStringMaxLength(value: String, maxLength: Int, errorList: MutableList<ErrorCode>) {
        if (value.length > maxLength) {
            errorList.add(ErrorCode.TOO_MUCH_LONG_STRING)
        }
    }
}

class FixLengthStringValidatorUtil {
    fun validateStringFixLength(value: String, fixLength: Int, errorList: MutableList<ErrorCode>) {
        if (value.length != fixLength) {
            errorList.add(ErrorCode.INVALID_VALUE_LENGTH)
        }
    }
}

class OnlyDigitStringValidatorUtil {
    private companion object {
        private val ONLY_NUMBERS_REGEX = "\\d+".toRegex()
    }
    fun validateStringOnlyNumber(value: String, errorList: MutableList<ErrorCode>) {
        if (!ONLY_NUMBERS_REGEX.matches(value)) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
        }
    }
}

private fun String?.validate(emptyStringValidator: Validator<String>): MutableList<ErrorCode> =
    mutableListOf(*emptyStringValidator.validate(this).toTypedArray())
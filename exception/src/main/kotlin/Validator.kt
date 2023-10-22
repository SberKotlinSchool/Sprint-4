abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>

    fun endValidateErrors(validator: ErrorCode, errors: ArrayList<ErrorCode>): ArrayList<ErrorCode> {
        if (errors.size > 0) {
            errors.add(0, validator)
            errors.add(ErrorCode.END_ERROR)
        }

        return errors
    }
}

private const val NAME_LENGTH: Int = 16
private const val PHONE_LENGTH: Int = 11
private const val EMAIL_LENGTH: Int = 32
private const val SNILS_LENGTH: Int = 11

private val NAME_VALIDATOR_REGEXP: Regex = Regex("^[а-яА-Я]{1,16}\$")
private val PHONE_VALIDATOR_REGEXP: Regex = Regex("^[7|8]([0-9]{10})\$")
private val EMAIL_VALIDATOR_REGEXP: Regex = Regex("^(\\w{2,}@\\w{2,}\\.[a-z]{2,5})\$")
private val SNILS_VALIDATOR_REGEXP: Regex = Regex("^\\d{11}\$")

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        var errorList: ArrayList<ErrorCode> = ArrayList()

        if (value != null) {
            if (value?.length > NAME_LENGTH)
                errorList.add(ErrorCode.INVALID_LENGTH)

            if (!value.matches(NAME_VALIDATOR_REGEXP))
                errorList.add(ErrorCode.INVALID_CHARACTER)
        } else
            errorList.add(ErrorCode.INVALID_CHARACTER)

        errorList = endValidateErrors(ErrorCode.INVALID_NAME, errorList)

        return errorList
    }

}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        var errorList: ArrayList<ErrorCode> = ArrayList()

        if (value != null) {
            if (value?.length!! != PHONE_LENGTH)
                errorList.add(ErrorCode.INVALID_LENGTH)

            if (!value.matches(PHONE_VALIDATOR_REGEXP))
                errorList.add(ErrorCode.INVALID_CHARACTER)
        } else
            errorList.add(ErrorCode.INVALID_CHARACTER)

        errorList = endValidateErrors(ErrorCode.INVALID_PHONE, errorList)

        return errorList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        var errorList: ArrayList<ErrorCode> = ArrayList()

        if (value != null) {
            if (value?.length!! > EMAIL_LENGTH)
                errorList.add(ErrorCode.INVALID_LENGTH)

            if (!value.matches(EMAIL_VALIDATOR_REGEXP))
                errorList.add(ErrorCode.INVALID_CHARACTER)
        } else
            errorList.add(ErrorCode.INVALID_CHARACTER)

        errorList = endValidateErrors(ErrorCode.INVALID_EMAIL, errorList)

        return errorList
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        var errorList: ArrayList<ErrorCode> = ArrayList()

        if (value != null) {
            if (value?.length!! != SNILS_LENGTH)
                errorList.add(ErrorCode.INVALID_LENGTH)

            if (!value.matches(SNILS_VALIDATOR_REGEXP))
                errorList.add(ErrorCode.INVALID_CHARACTER)

            if (!checkSNILSControlSum(value))
                errorList.add(ErrorCode.INVALID_SNILS_CONTROL_SUM)
        }
        else errorList.add(ErrorCode.INVALID_CHARACTER)

    errorList = endValidateErrors(ErrorCode.INVALID_SNILS, errorList)

        return errorList
    }

    private fun checkSNILSControlSum(value: String): Boolean {
        val expectedSum: Int = value.substring(9).toInt()
        val actualSum: Int = value.substring(0, 9).reversed()
            .mapIndexed { index, symbol -> symbol.digitToInt() * (index + 1) }
            .sum()

        return when (actualSum) {
            in 0..99 -> actualSum == expectedSum
            100, 101 -> return actualSum == 0
            else -> actualSum % 101 == expectedSum
        }
    }
}
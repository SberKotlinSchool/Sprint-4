import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return listOf(ErrorCode.INVALID_NULL_VALUE)
        }

        val errorList = mutableListOf<ErrorCode>()

        if (value.isEmpty() || value.length != 11) {
            errorList.add(ErrorCode.INVALID_LENGTH)
        }

        if (value.all { !it.isDigit() }) {
            errorList.add(ErrorCode.NOT_DIGITS_IN_PHONE_NUMBER)
        }

        if (!value.startsWith("7") && !value.startsWith("8")) {
            errorList.add(ErrorCode.INVALID_FIRST_DIGIT)
        }

        return errorList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return listOf(ErrorCode.INVALID_NULL_VALUE)
        }

        val errorList = mutableListOf<ErrorCode>()

        if (value.isEmpty() || value.length > 32) {
            errorList.add(ErrorCode.INVALID_LENGTH)
        }

        if (!Pattern.compile(
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
            ).matcher(value).matches()
        ) {
            errorList.add(ErrorCode.INVALID_EMAIL)
        }

        return errorList
    }
}

class NameValidator() : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return listOf(ErrorCode.INVALID_NULL_VALUE)
        }

        val errorList = mutableListOf<ErrorCode>()

        if (value.isEmpty() || value.length > 16) {
            errorList.add(ErrorCode.INVALID_LENGTH)
        }

        if (!value.toList().stream()
                .allMatch { c: Char -> Pattern.compile("[а-яА-Я]").matcher(c.toString()).matches() }
        ) {
            errorList.add(ErrorCode.INVALID_CYRILLIC)
        }

        return errorList
    }
}

class SnilsValidator() : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return listOf(ErrorCode.INVALID_NULL_VALUE)
        }

        val errorList = mutableListOf<ErrorCode>()

        if (value.isEmpty() || value.length != 11) {
            errorList.add(ErrorCode.INVALID_LENGTH)
        }

        val snilsNumber = mutableListOf<Int>()
        value.onEachIndexed { index, c -> snilsNumber.add(c.digitToInt()) }
        val controlSum = ("" + value[9] + value[10]).toInt()
        var sum = 0
        var k = 9
        for (i in 0..8) {
            sum += snilsNumber[i] * k--
        }

        if (
            (sum < 100 && sum != controlSum) || (sum == 100 && controlSum != 0)
        ) {
            errorList.add(ErrorCode.INVALID_SNILS_CHECKSUM)
        } else if (sum > 100) {
            val rest = sum % 101
            if (rest == 100 && controlSum != 0) {
                errorList.add(ErrorCode.INVALID_SNILS_CHECKSUM)
            } else if (rest != controlSum) {
                errorList.add(ErrorCode.INVALID_SNILS_CHECKSUM)
            }
        }

        return errorList
    }
}
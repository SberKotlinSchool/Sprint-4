import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        mutableListOf<ErrorCode>().apply {
            if (it.length != 11) add(ErrorCode.INVALID_LENGTH)
            if (it.none { char -> char.isDigit() }) add(ErrorCode.NON_DIGITS)
            if (it.first() !in listOf('7', '8')) add(ErrorCode.INVALID_FIRST_DIGIT)
        }
    } ?: listOf(ErrorCode.INVALID_NULL_VALUE)
}

class EmailValidator : Validator<String>() {
    companion object {
        val EMAIL_PATTERN = Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        )
    }

    override fun validate(value: String?): List<ErrorCode> = value?.let {
        mutableListOf<ErrorCode>().apply {
            if (it.length > 32 || !EMAIL_PATTERN.matcher(it).matches()) add(ErrorCode.INVALID_EMAIL)
        }
    } ?: listOf(ErrorCode.INVALID_NULL_VALUE)
}

class NameValidator : Validator<String>() {
    companion object {
        val CYRILLIC_PATTERN = Pattern.compile("[а-яА-Я]")
    }

    override fun validate(value: String?): List<ErrorCode> = value?.let {
        mutableListOf<ErrorCode>().apply {
            if (it.length > 16) add(ErrorCode.INVALID_LENGTH)
            if (it.any { char -> !CYRILLIC_PATTERN.matcher(char.toString()).matches() }) add(ErrorCode.INVALID_CYRILLIC)
        }
    } ?: listOf(ErrorCode.INVALID_NULL_VALUE)
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        mutableListOf<ErrorCode>().apply {
            if (it.length != 11) {
                add(ErrorCode.INVALID_LENGTH)
            } else if (it.any { char -> !char.isDigit() }) {
                add(ErrorCode.NON_DIGITS)
            } else {
                val snilsDigits = it.map { char -> char.digitToInt() }
                val checksum = ("" + it[9] + it[10]).toInt()
                val sum = snilsDigits.subList(0, 9).withIndex().sumOf { (index, digit) -> digit * (9 - index) }
                when {
                    sum < 100 && sum != checksum -> add(ErrorCode.INVALID_SNILS_CHECKSUM)
                    sum == 100 && checksum != 0 -> add(ErrorCode.INVALID_SNILS_CHECKSUM)
                    sum > 100 && sum % 101 != checksum -> add(ErrorCode.INVALID_SNILS_CHECKSUM)
                }
            }
        }
    } ?: listOf(ErrorCode.INVALID_NULL_VALUE)
}


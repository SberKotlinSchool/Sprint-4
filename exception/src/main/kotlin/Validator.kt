import java.util.Collections


abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return Collections.singletonList(ErrorCode.FIRST_NAME_NULL_ERROR)
        }
        val result: MutableList<ErrorCode> = mutableListOf()
        if (value.length > 16) {
            result.add(ErrorCode.FIRST_NAME_TOO_LONG_ERROR)
        }
        if (!value.isCyrillic()) {
            result.add(ErrorCode.FIRST_NAME_NOT_CYRILLIC_ERROR)
        }
        return result
    }
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return Collections.singletonList(ErrorCode.LAST_NAME_NULL_ERROR)
        }
        val result: MutableList<ErrorCode> = mutableListOf()
        if (value.length > 16) {
            result.add(ErrorCode.LAST_NAME_TOO_LONG_ERROR)
        }
        if (!value.isCyrillic()) {
            result.add(ErrorCode.LAST_NAME_NOT_CYRILLIC_ERROR)
        }
        return result
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return Collections.singletonList(ErrorCode.PHONE_NULL_ERROR)
        }
        val result: MutableList<ErrorCode> = mutableListOf()
        if (!value.isNumbers()) {
            result.add(ErrorCode.PHONE_NOT_NUMBERS_ERROR)
        }
        if (value.length != 11) {
            result.add(ErrorCode.PHONE_NOT_CORRECT_LENGTH_ERROR)
        }
        if (!(value.startsWith("7") || value.startsWith("8"))) {
            result.add(ErrorCode.PHONE_NOT_STARTS_FROM_7_OR_8_ERROR)
        }
        return result
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return Collections.singletonList(ErrorCode.EMAIL_NULL_ERROR)
        }
        val result: MutableList<ErrorCode> = mutableListOf()
        if (value.length > 32) {
            result.add(ErrorCode.EMAIL_TOO_LONG_ERROR)
        }
        if (!value.matches("^[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$".toRegex())) {
            result.add(ErrorCode.EMAIL_WRONG_FORMAT_ERROR)
        }
        return result
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) {
            return Collections.singletonList(ErrorCode.SNILS_NULL_ERROR)
        }
        val result: MutableList<ErrorCode> = mutableListOf()
        if (value.length != 11) {
            result.add(ErrorCode.SNILS_WRONG_LENGTH_ERROR)
        }
        if (!value.isNumbers()) {
            result.add(ErrorCode.SNILS_NOT_NUMBERS_ERROR)
        } else {
            if (!isSnilsCheckSumValid(value)) {
                result.add(ErrorCode.SNILS_CHECKSUM_ERROR)
            }
        }
        return result
    }

    private fun isSnilsCheckSumValid(snils: String): Boolean {
        var sum = 0
        snils.take(9).reversed()
                .map { it.digitToInt() }
                .forEachIndexed { index, i -> sum += i * (index + 1) }
        while (sum >= 100) {
            if (sum == 100) {
                sum = 0
            } else {
                sum %= 101
            }
        }
        return snils.takeLast(2).toInt() == sum
    }
}

private const val CYRILLIC_ALPHABET = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
private const val LATIN_ALPHABET = "abcdefghijklmnopqrstuvwxyz"

private fun Char.isCyrillic(): Boolean {
    return CYRILLIC_ALPHABET.contains(this.lowercaseChar())
}

private fun String.isCyrillic(): Boolean {
    for (c in this) {
        if (!c.isCyrillic()) {
            return false
        }
    }
    return true
}

private fun String.isNumbers(): Boolean {
    for (c in this) {
        if (!c.isDigit()) {
            return false
        }
    }
    return true
}
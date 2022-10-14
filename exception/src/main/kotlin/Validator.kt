import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()

        value?.let {
            with (it) {
                if (!checkSymbols())
                    result.add(ErrorCode.INVALID_CHARACTER)
                if (!checkLength())
                    result.add(ErrorCode.LENGTH)
                try {
                    if (isNeedToCheckNumber() && !checkNumber())
                        result.add(ErrorCode.SNILS_CHECK_NUMBER)
                } catch (t: Throwable) { }
            }
        } ?: result.add(ErrorCode.EMPTY_INPUT)

        return result
    }

    private fun String.isNeedToCheckNumber(): Boolean = dropLast(2).toLong() > 1001998

    private fun String.checkNumber(): Boolean =
        dropLast(2).reversed().foldIndexed(0L) { index, acc, c ->
            (acc + c.digitToInt() * (index + 1))
        } % 101 % 100 == takeLast(2).toLong()

    private fun String.checkSymbols(): Boolean = Pattern.matches("[0-9]+", this)

    private fun String.checkLength(): Boolean = length == 11
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()

        value?.let {
            with (it) {
                if (!checkSymbols())
                    result.add(ErrorCode.INVALID_CHARACTER)
                if (!checkLength())
                    result.add(ErrorCode.LENGTH)
                if (!checkDomain())
                    result.add(ErrorCode.DOMAIN)
            }
        } ?: result.add(ErrorCode.EMPTY_INPUT)

        return result
    }

    private fun String.checkSymbols(): Boolean = Pattern.matches("[A-Za-z@.]+", this)

    private fun String.checkLength(): Boolean = length <= 32

    private fun String.checkDomain(): Boolean = split("@")
        .getOrElse(1) { "" }
        .isNotEmpty()
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()

        value?.let {
            with (it) {
                if (!checkSymbols())
                    result.add(ErrorCode.INVALID_CHARACTER)
                if (!checkLength())
                    result.add(ErrorCode.LENGTH)
            }
        } ?: result.add(ErrorCode.EMPTY_INPUT)

        return result
    }

    private fun String.checkSymbols(): Boolean = Pattern.matches("[А-Яа-я]+", this)

    private fun String.checkLength(): Boolean = length <= 16
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result = mutableListOf<ErrorCode>()

        value?.let {
            with (it) {
                if (!checkFirstSymbol() || !checkSymbols())
                    result.add(ErrorCode.INVALID_CHARACTER)
                if (!checkLength())
                    result.add(ErrorCode.LENGTH)
            }
        } ?: result.add(ErrorCode.EMPTY_INPUT)

        return result
    }

    private fun String.checkFirstSymbol(): Boolean =
        when (first()) {
            '7', '8' -> true
            else -> false
        }

    private fun String.checkSymbols(): Boolean = Pattern.matches("[0-9]+", this)

    private fun String.checkLength(): Boolean = length == 11
}
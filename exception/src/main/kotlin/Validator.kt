import java.lang.Integer.parseInt

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    val PATTERN = "[7,8][0-9]{10}".toRegex()

    override fun validate(value: String?): List<ErrorCode> {
        return if (value?.matches(PATTERN) != false) emptyList()
               else listOf(ErrorCode.INVALID_PHONE)
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        var isValid = value?.length ?: -1 <= 16
        value?.chars()
            ?.mapToObj(Character.UnicodeBlock::of)
            ?.filter { !Character.UnicodeBlock.CYRILLIC.equals(it) }
            ?.findAny()
            ?.ifPresent { isValid = false } // если что-то помимо кириллицы
        return if (isValid) emptyList()
               else listOf(ErrorCode.INVALID_NAME)
    }
}

class EmailValidator : Validator<String>() {
    val PATTERN = "(\\S+)@(\\S+)\\.(\\S+)".toRegex()
    override fun validate(value: String?): List<ErrorCode> {
        return if ((value?.length ?: -1 <= 32) && (value?.matches(PATTERN) != false)) emptyList()
               else listOf(ErrorCode.INVALID_EMAIL)
    }

}

class SnilsValidator : Validator<String>() {
    val PATTERN = "[0-9]{11}".toRegex()
    override fun validate(value: String?): List<ErrorCode> {
        return if (value?.matches(PATTERN) != false && isSnilsValid(value)) emptyList()
               else listOf(ErrorCode.INVALID_SNILS)
    }

    fun isSnilsValid(snils: String?): Boolean {
        // Проверка контрольного числа Страхового номера проводится только для номеров больше номера 001-001-998
        if (snils?.matches("001001[0-9]{2}[0-8]{1}[0-9]{2}".toRegex()) != false) return true
        val checkSum = parseInt(snils[9].toString() + snils[10].toString())
        var sum = 0
        for (i in 0..8) {
            sum += parseInt(snils[i].toString()) * (9 - i)
        }
        // Получить остаток от деления на 101. Если получилось 100, контрольное число равно 0
        sum = if (sum % 101 == 100) 0 else sum % 101
        return sum == checkSum
    }
}
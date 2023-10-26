abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {

    override fun validate(value: String?): List<ErrorCode> {
        val regexName = """^[7,8][0-9]{10}$""".toRegex()
        if (value == null
                || !regexName.containsMatchIn(value)) {
            return listOf(ErrorCode.INVALID_PHONE)
        }
        return listOf()
    }
}


class NameValidator : Validator<String>() {

    override fun validate(value: String?): List<ErrorCode> {
        val regexName = """^[а-яА-Я]{1,16}$""".toRegex()
        if (value == null
                || !regexName.containsMatchIn(value)) {
            return listOf(ErrorCode.INVALID_NAME)
        }
        return listOf()
    }
}


class EmailValidator : Validator<String>() {

    override fun validate(value: String?): List<ErrorCode> {
        val regex = """^[a-zA-Z]+@[a-zA-Z]+$""".toRegex()
        if (value == null
                || value.isEmpty()
                || value.length > 32
                || !regex.containsMatchIn(value)) {
            return listOf(ErrorCode.INVALID_EMAIL)
        }
        return listOf()
    }
}


class SnilsValidator : Validator<String>() {

    override fun validate(value: String?): List<ErrorCode> {
        val regex = """^\d{3}-\d{3}-\d{3} \d{2}$""".toRegex()
        if (value == null
                || !regex.containsMatchIn(value)
                || !checkSum(value)) {
            return listOf(ErrorCode.INVALID_SNILS)
        }
        return listOf()
    }

    /**
    Алгоритм формирования контрольного числа СНИЛС таков:

    Каждая цифра СНИЛС умножается на номер своей позиции (позиции отсчитываются с конца, то есть, справа)
    Полученные произведения суммируются
    Получить остаток от деления на 101
    Если получилось 100, контрольное число равно 0
    Например: указан СНИЛС 112-233-445 95. Проверяем правильность контрольного числа:

    цифры номера 1 1 2 2 3 3 4 4 5
    номер позиции 9 8 7 6 5 4 3 2 1
    Сумма = 1×9 + 1×8 + 2×7 + 2×6 + 3×5 + 3×4 + 4×3 + 4×2 + 5×1 = 95. Сумма равна YY (контрольное число). Контрольное число 95 — указано верно.
     */
    private fun checkSum(snils: String): Boolean {
        var numPos = 9
        var sum = 0

        snils.forEach {
            if (numPos > 0 && it != '-') {
                sum += Integer.parseInt(it.toString()) * numPos
                numPos--
            }
        }
        sum = sum % 101
        if (sum == 100) {
            sum = 0
        }

        val checkNum = Integer.parseInt(snils.substring(12))

        return sum == checkNum
    }

}

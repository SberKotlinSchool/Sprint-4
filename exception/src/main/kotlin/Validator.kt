abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result: MutableList<ErrorCode> = mutableListOf()

        if (value.isNullOrEmpty()) {
            result.add(ErrorCode.NULL_OR_EMPTY_PHONE)
            return result
        }

        if (!Regex("""[7,8]\d{10}""").matches(value))
            result.add(ErrorCode.INVALID_CHARACTER_IN_PHONE)

        return result
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result: MutableList<ErrorCode> = mutableListOf()

        if (value.isNullOrEmpty()) {
            result.add(ErrorCode.NULL_OR_EMPTY_EMAIL)
            return result
        }

        if (!Regex("""\w+([.-]?\w+)*@\w+([.-]?\w+)*\.\w{2,4}""").matches(value)
                .and(value.length < 33))
            result.add(ErrorCode.INVALID_CHARACTER_IN_EMAIL)

        return result
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result: MutableList<ErrorCode> = mutableListOf()

        if (value.isNullOrEmpty()) {
            result.add(ErrorCode.NULL_OR_EMPTY_SNILS)
            return result
        }

        if (!Regex("""\d{11}""").matches(value).and(isCorrectChecksum(value)))
            result.add(ErrorCode.INVALID_CHARACTER_IN_SNILS)

        return result
    }

    private fun isCorrectChecksum(snils : String) : Boolean {
        val snilsNumber = try {
            snils.toLong()
        } catch (e : NumberFormatException) {
            return false
        }

        //http://www.consultant.ru/document/cons_doc_LAW_124607/68ac3b2d1745f9cc7d4332b63c2818ca5d5d20d0/
        //Проверка контрольного числа Страхового номера проводится только для номеров больше номера 001-001-998
        if ((snilsNumber / 100) < 1001998) return false

        val checksum = (snilsNumber % 100).toInt()
        var calculateSum = 0

        //каждая цифра Страхового номера умножается на номер своей позиции (позиции отсчитываются с конца)
        //полученные произведения суммируются
        for (i in 1..9) calculateSum += snils[i - 1].digitToInt() * (10 - i)

        //сумма делится на 101
        while (calculateSum > 99) {
            when (calculateSum) {
                100 -> calculateSum = 0
                101 -> calculateSum = 0
                else -> calculateSum %= 101
            }
        }

        return calculateSum == checksum
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val result: MutableList<ErrorCode> = mutableListOf()

        if (value.isNullOrEmpty()) {
            result.add(ErrorCode.NULL_OR_EMPTY_NAME)
            return result
        }

        if (!Regex("""[а-яёА-ЯЁ]{1,16}""").matches(value))
            result.add(ErrorCode.INVALID_CHARACTER_IN_NAME)

        return result
    }

}
abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> = value?.let {
        mutableListOf<ErrorCode>().apply {
            if (it.length != 11) add(ErrorCode.INVALID_COUNT_CHARACTER)
            if (it.first() !in listOf('7', '8')) add(ErrorCode.INVALID_PHONE_NUMBER)
            if (it.none { c ->  c.isDigit()}) add(ErrorCode.INVALID_PHONE_NUMBER)
        }
    } ?: listOf(ErrorCode.EMPTY_FIELD)
}


class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.EMPTY_FIELD)
        if (!"""[А-Яа-я]{0,16}""".toRegex().matches(value)) return listOf(ErrorCode.INVALID_FIRST_NAME)
        return emptyList()
    }
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.EMPTY_FIELD)
        if (!"""[А-Яа-я]{0,16}""".toRegex().matches(value)) return listOf(ErrorCode.INVALID_LAST_NAME)
        return emptyList()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.EMPTY_FIELD)
        if (!"""[A-Za-z]+@[a-z]{0,32}.[a-z]{0,3}""".toRegex().matches(value)) return listOf(ErrorCode.INVALID_EMAIL)
        return emptyList()
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.EMPTY_FIELD)
        if (!"""\d{11}""".toRegex().matches(value)) return listOf(ErrorCode.INVALID_SNILS)
        if (!checkSum(value)) return listOf(ErrorCode.INVALID_SNILS)
        return emptyList()
    }

    private fun checkSum(snils: String): Boolean {
        val s = snils.substring(9, 11).toInt()
        var sum: Long = 0
        (0..8).forEach { sum += (9 - it) * (snils[it].code - '0'.code) }
        return sum % 101 % 100 == s.toLong()
    }
}
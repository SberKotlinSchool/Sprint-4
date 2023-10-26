abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.NULL_OR_EMPTY)
        if (!"""([78])\d{10}""".toRegex().matches(value)) return listOf(ErrorCode.INVALID_CHARACTER)
        return emptyList()
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.NULL_OR_EMPTY)
        if (!"""[А-Яа-я]{0,16}""".toRegex().matches(value)) return listOf(ErrorCode.INVALID_CHARACTER)
        return emptyList()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.NULL_OR_EMPTY)
        if (!"""[A-Za-z]+@[a-z]{0,32}.[a-z]{0,3}""".toRegex().matches(value)) return listOf(ErrorCode.INVALID_CHARACTER)
        return emptyList()
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrEmpty()) return listOf(ErrorCode.NULL_OR_EMPTY)
        if (!"""\d{11}""".toRegex().matches(value)) return listOf(ErrorCode.INVALID_CHARACTER)
        if (!calcCheckSum(value)) return listOf(ErrorCode.INVALID_CHECKSUM)
        return emptyList()
    }

    private fun calcCheckSum(snils: String): Boolean {
        val s = snils.substring(9, 11).toInt()
        var sum: Long = 0
        (0..8).forEach { sum += (9 - it) * (snils[it].code - '0'.code) }
        return sum % 101 % 100 == s.toLong()
    }

}
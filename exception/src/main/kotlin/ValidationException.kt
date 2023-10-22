class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Invalid symbol"),
    EMPTY_FIELD(101, "Empty field"),
    INVALID_LENGTH(102, "Invalid len"),
    NULL_FIELD(103, "Field doesn't set"),
    INVALID_CONTROL_NUMBER(104, "Invalid SNILS")
}

interface Validation<E> {
    fun validate(data: E): ErrorCode?
}

class NotEmptyStringValidation : Validation<String> {
    override fun validate(data: String): ErrorCode? {
        if (data.trim() == "")
            return ErrorCode.EMPTY_FIELD
        return null
    }
}

class ExactLengthStringValidation(private val expectedLength: Int) : Validation<String> {
    override fun validate(data: String): ErrorCode? {
        if (data.length != expectedLength)
            return ErrorCode.INVALID_LENGTH
        return null
    }
}

class MatchRegexStringValidation(private val expectedRegex: String) : Validation<String> {
    override fun validate(data: String): ErrorCode? {
        if (!data.matches(Regex(expectedRegex)))
            return ErrorCode.INVALID_CHARACTER
        return null
    }
}

class MaxLengthStringValidation(private val maxLength: Int) : Validation<String> {
    override fun validate(data: String): ErrorCode? {
        if (data.length > maxLength)
            return ErrorCode.INVALID_LENGTH
        return null
    }
}

class SnilsControlNumberStringValidation : Validation<String> {
    override fun validate(data: String): ErrorCode? {
        if (!data.matches(Regex("\\d{11}")))
            return null

        val len = 9
        val calculated = getNum(data)

        val real = data.substring(len, data.length).toInt()

        if (calculated != real)
            return ErrorCode.INVALID_CONTROL_NUMBER

        return null
    }

    private fun getNum(data: String, len: Int = 9) =
        data.substring(0, len).split("")
        .filter { it.isNotEmpty() }
        .mapIndexed { i, s -> (len - i) * s.toInt() }
        .sum().mod(101)
}
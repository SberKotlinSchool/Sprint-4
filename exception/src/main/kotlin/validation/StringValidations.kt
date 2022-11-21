package validation

import ErrorCode

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

        val numberLength = 9
        val calculatedControlNumber = calculateControlNumber(data, numberLength)

        val readControlNumber = data.substring(numberLength, data.length).toInt()

        if (calculatedControlNumber != readControlNumber)
            return ErrorCode.INVALID_CONTROL_NUMBER

        return null
    }

    private fun calculateControlNumber(data: String, numberLength: Int) = data.substring(0, numberLength)
        .split("")
        .filter { s -> s.isNotEmpty() }
        .mapIndexed { i, s -> (numberLength - i) * s.toInt() }
        .sum()
        .mod(101)
}

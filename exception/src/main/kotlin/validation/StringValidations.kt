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

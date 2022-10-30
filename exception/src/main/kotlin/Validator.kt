abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null || value.trim() == "")
            return listOf(ErrorCode.EMPTY_FIELD)

        val resultErrors = ArrayList<ErrorCode>()

        if (value.length != 11)
            resultErrors.add(ErrorCode.INVALID_LENGTH)
        if (!value.matches(Regex("[7|8]\\d+")))
            resultErrors.add(ErrorCode.INVALID_CHARACTER)

        return resultErrors
    }
}

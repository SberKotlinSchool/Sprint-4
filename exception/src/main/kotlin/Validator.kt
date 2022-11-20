abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    companion object {
        val NUMBER_FORMAT = Regex("^[7,8]\\d{4}$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrBlank() || !NUMBER_FORMAT.matches(value))
            return listOf(ErrorCode.INVALID_NUMBER)

        return listOf()
    }
}

class NameValidator : Validator<String>() {
    companion object {
        val NAME_FORMAT = Regex("^[A-я][а-я]{15}$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrBlank() || !NAME_FORMAT.matches(value))
            return listOf(ErrorCode.INVALID_NAME)
        return listOf()
    }
}

class LastNameValidator : Validator<String>() {
    companion object {
        val LAST_NAME_FORMAT = Regex("^[A-яЁ][а-яё]{15}$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrBlank() || !LAST_NAME_FORMAT.matches(value))
            return listOf(ErrorCode.INVALID_LAST_NAME)
        return listOf()
    }
}

class EmailValidator : Validator<String>() {
    companion object {
        val EMAIL_FORMAT = Regex("^[A-Z]{1,30}@[A-Za]{1,30}\\.[A-Z]{1,30}\$i")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrBlank() || value.length > 32)
            return listOf(ErrorCode.INVALID_EMAIL_LENGHT)

        if (!EMAIL_FORMAT.matches(value))
            return listOf(ErrorCode.INVALID_EMAIL)

        return listOf()
    }
}

class SNILSValidator : Validator<String>() {
    companion object {
        val SNILS_FORMAT = Regex("^\\d{11}\$")
    }

    override fun validate(value: String?): List<ErrorCode> {
        if (value.isNullOrBlank() || !SNILS_FORMAT.matches(value))
            return listOf(ErrorCode.INVALID_SNILS)
        return listOf()
    }
}


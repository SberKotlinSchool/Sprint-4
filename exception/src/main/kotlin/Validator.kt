
abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        requireNotNull(value) { return listOf(ErrorCode.INVALID_FIRST_NAME) }
        require(( value.lowercase().matches("\\W+[а-я]".toRegex()) )
                and (value.trim().length <= 16))
        { return listOf(ErrorCode.INVALID_FIRST_NAME) }
        return listOf()
    }
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        requireNotNull(value) { return listOf(ErrorCode.INVALID_LAST_NAME) }
        require(( value.lowercase().matches("\\W+[а-я]".toRegex()) )
                and (value.trim().length <= 16))
        { return listOf(ErrorCode.INVALID_LAST_NAME) }
        return listOf()
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        requireNotNull(value) { return listOf(ErrorCode.INVALID_PHONE) }
        require(( (value.startsWith("7") )
                or ( value.startsWith("8") ))
                and (value.trim().length == 11))
        { return listOf(ErrorCode.INVALID_PHONE) }
        return listOf()
    }
}

class EmailValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        requireNotNull(value) { return listOf(ErrorCode.INVALID_EMAIL) }
        require(( value.lowercase().matches("[a-z0-9._%-]+@[a-z0-9.-]+\\.[a-z]{2,}".toRegex()) )
                and (value.trim().length <= 32))
        { return listOf(ErrorCode.INVALID_EMAIL) }
        return listOf()
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        requireNotNull(value) { return listOf(ErrorCode.INVALID_SNILS) }
        require(( value.matches("[0-9]+".toRegex()) )
                and (value.trim().length == 11))
        { return listOf(ErrorCode.INVALID_SNILS) }
        return listOf()
    }
}
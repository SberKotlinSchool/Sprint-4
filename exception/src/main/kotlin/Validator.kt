abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class FioValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value?.matches("^[а-яА-Я]{1,16}$".toRegex()) == true) return emptyList()
        return listOf(ErrorCode.INVALID_FIO_FORMAT)
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value?.matches("^[7|8]+[0-9]{10}$".toRegex()) == true) return emptyList()
        return listOf(ErrorCode.INVALID_PHONE_FORMAT)
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value?.matches("(?=.{3,32}\$)[a-zA-Z.]+@[a-zA-Z.]+.[a-zA-Z.]$".toRegex()) == true) return emptyList()
        return listOf(ErrorCode.INVALID_EMAIL_FORMAT)
    }
}

class SNILSValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value?.matches("^[0-9]{11}$".toRegex()) == true) return emptyList()
        return listOf(ErrorCode.INVALID_SNILS_FORMAT)
    }
}
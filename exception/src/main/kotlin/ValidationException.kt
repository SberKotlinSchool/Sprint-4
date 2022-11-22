class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_EMAIL(101, "Недопустимый e-mail"),
    INVALID_SNILS(102, "Недопустимый СНИЛС"),
    INVALID_PHONE(103, "Недопустимый номер телефона"),
}
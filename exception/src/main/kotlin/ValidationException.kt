class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_NAME(101, "Недопустимый формат имени/фамилии"),
    INVALID_PHONE_NUMBER(102, "Недопустимый номер телефона"),
    INVALID_EMAIL(102, "Недопустимый формат Email"),
    INVALID_SNILS(102, "Недопустимый формат Email"),
}
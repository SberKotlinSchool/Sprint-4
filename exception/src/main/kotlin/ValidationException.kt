class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE(100, "Недопустимый телефон. Ожидается 11 цифр начиная с 7 или 8"),
    INVALID_FIRST_NAME(210, "Недопустимое имя. Ожидается до 16 кирилических символов"),
    INVALID_LAST_NAME(220, "Недопустимая фамилия. Ожидаются до 16 кирилических символов"),
    INVALID_EMAIL(300, "Недопустимый email. Ожидается длина до 16 символов и соответствие шаблону w+@w+(.w+)+"),
    INVALID_SNILS(400, "Недопустимый снилс. Ожидается 11 цифр."),
}
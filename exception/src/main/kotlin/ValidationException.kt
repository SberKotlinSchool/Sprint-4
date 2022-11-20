class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_NUMBER(101, "Недопустимый номер телефона"),
    INVALID_NAME(102, "Имя содержит недопустимые символы"),
    INVALID_LAST_NAME(103, "Фамилия содержит недопустимые символы"),
    INVALID_EMAIL(104, "Недопустимая электронная почта"),
    INVALID_EMAIL_LENGHT(105, "Недопустимая длина электронной почты"),
    INVALID_SNILS(106, "Недопустимый СНИЛС");
}
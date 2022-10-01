class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_FIRST_NAME(100, "Некорректное имя"),
    INVALID_LAST_NAME(100, "Некорректная фамилия"),
    INVALID_PHONE_NUMBER(100, "Некорректный номер телефона"),
    INVALID_EMAIL(100, "Некорректный имейл"),
    INVALID_SNILS(100, "Некорректный СНИЛС")
    // ...
}
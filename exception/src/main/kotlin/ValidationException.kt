class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_PHONE(110, "Недопустимый телефон!"),
    INVALID_FIRST_NAME(120, "Недопустимое имя!"),
    INVALID_LAST_NAME(130, "Недопустимая фамилия!"),
    INVALID_EMAIL(140, "Недопустимый email!"),
    INVALID_SNILS(150, "Недопустимый снилс!")
}
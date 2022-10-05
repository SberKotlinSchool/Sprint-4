class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    EMPTY_CONTENT(200, "Пустое поле"),
    INVALID_LENGTH(300, "Недопустимая длина")
    // ...
}
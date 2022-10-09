class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    EMPTY_CONTENT(200, "Пустое поле"),
    INVALID_LENGTH(300, "Недопустимая длина"),
    INVALID_SNILS(400, "Ошибка данных в СНИЛС"),
    INVALID_EMAIL(500, "Ошибка данных в email")
    // ...
}
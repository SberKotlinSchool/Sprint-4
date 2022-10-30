class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    EMPTY_FIELD(101, "Пустое поле"),
    INVALID_LENGTH(102, "Недопустимая длина"),
    NULL_FIELD(103, "Поле не задано")
}

class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    EMPTY_FIELD(101, "Отсутствуют данные"),
    INVALID_LENGTH(102, "Недопустимая длина")
}

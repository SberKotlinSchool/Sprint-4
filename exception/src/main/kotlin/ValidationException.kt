class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    NULL_OR_EMPTY(101, "Пустая строка"),
    INVALID_CHECKSUM(102, "Неправильная контрольная сумма")
}
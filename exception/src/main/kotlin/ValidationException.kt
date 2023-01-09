class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    NULL_OR_EMPTY(101, "Пустая строка"),
    INVALID_CHECK_SUM(102, "Контрольная сумма неверна")
    // ...
}
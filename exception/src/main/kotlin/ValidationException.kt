class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    EXCEEDED_LENGTH(101, "Превышена длина"),
    INVALID_LENGTH(102, "Недопустимая длина"),
    INVALID_CHECK_SUM(103, "Несовпадение контрольного числа"),
    INVALID_FORMAT(104, "Недопустимый формат")
}
class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    NULL_VALUE(101, "value cannot be null"),
    INVALID_NUMBER(102, "invalid number"),
    INVALID_NAME(103, "invalid name"),
    INVALID_EMAIL(104, "invalid email"),
    INVALID_SNILS(105, "invalid snils")
}
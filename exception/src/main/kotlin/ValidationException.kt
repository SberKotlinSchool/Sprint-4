class ValidationException(val errorCode: List<Pair<String, List<ErrorCode>>>) :
        RuntimeException(errorCode.joinToString(";")
        { "${it.first}: ${it.second.joinToString(",") { error -> error.msg }}"})

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(101, "Недопустимая длина строки"),
    INVALID_CONTROL_NUMBER(102, "Невалидное контрольное число СНИЛС"),
    NULL_VALUE(103, "Значение поля не задано")
}
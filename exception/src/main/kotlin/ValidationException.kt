class ValidationException(val errorCodes: Map<String, List<ErrorCode>>) :
    RuntimeException(errorCodes.entries.joinToString(","))

enum class ErrorCode(val code: Int, val msg: String) {
    NULL_INPUT(0, "Пустой ввод"),
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(200, "Недопустимая длина"),
    INVALID_DOMAIN(300, "Недопустимый домен"),
    INVALID_CONTROL_NUMBER(400, "Неправильное контрольное число")
}
class ValidationException(val propDescMap: Map<String, List<ErrorCode>>) : RuntimeException(propDescMap.toString())

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(101, "Недопустимая длина"),
    INVALID_CONTROL_NUM(102, "Неверное контрольное число"),
    NULL_STRING(103, "NULL строка"),
    DOMAIN_NOT_FOUND(104, "Не смогли найти домен"), ;

    override fun toString(): String {
        return msg
    }
}

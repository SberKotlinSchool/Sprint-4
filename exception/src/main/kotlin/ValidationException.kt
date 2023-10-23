class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(" ") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_NAME(1001, "Name: "),
    INVALID_PHONE(1002, "Phone: "),
    INVALID_EMAIL(1003, "Email: "),
    INVALID_SNILS(1004, "SNILS: "),

    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(101, "Некорректная длина"),
    INVALID_SNILS_CONTROL_SUM(102, "Некорректная контрольная сумма СНИЛС"),
    END_ERROR(200, ".")
}
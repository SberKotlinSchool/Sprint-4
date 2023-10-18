class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH( 101, "Некорректная длина" ),
    INVALID_FORMAT( 102, "Неверный формат" ),
    INVALID_SUM_SNILS(103, "Неверная контрольная сумма СНИЛС" )
    // ...
}
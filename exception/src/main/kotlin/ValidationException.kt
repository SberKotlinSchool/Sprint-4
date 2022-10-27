class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ")
    , INVALID_PHONE_NUMBER(101, "Недопустимый телефон")
    , INVALID_CYRILLIC(102, "Недопустимое имя")
    , INVALID_EMAIL(103, "Недопустимый email")
    , INVALID_SNILS(104, "Недопустимый СНИЛС")
    , INVALID_COUNT(104, "Недопустимая Длина строки")
    // ...
}
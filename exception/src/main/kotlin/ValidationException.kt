class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    TOO_MUCH_LONG_STRING(101, "Слишком длинная строка"),
    NULL_VALUE(102, "null значение"),
    EMPTY_VALUE(103, "Пустое значение"),
    INVALID_PHONE_CODE(104, "Некорректное значение телефонного кода"),
    INCORRECT_EMAIL_FORMAT(106, "Некорректный формат email"),
    INVALID_VALUE_LENGTH(107, "Строка имеет некорректную длину"),
    INVALID_SNILS_CONTROL_NUMBER(108, "Некорректное контрольное число СНИЛС")
}
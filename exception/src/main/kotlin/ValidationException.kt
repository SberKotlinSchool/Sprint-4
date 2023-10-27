class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    VALUE_TOO_BIG(101, "Длина строки превышает допустимое значение"),
    INVALID_EMAIL(102, "Некорректный email"),
    INVALID_SNILS(103, "Некорректный СНИЛС"),
    INCORRECT_STRING_LENGTH(104, "Некорректная длина строки"),
    DIGIT_ISSUE(105, "Отсутствие цифр в строке"),
    CYRILLIC_ISSUE(106, "Отсутсвтует кириллица в строке"),
    NULL_VALUE(107, "Значение не может быть null"),
}
class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_LENGTH(101, "Недопустимая длина поля"),
    INVALID_NULL_VALUE(102, "Значение null"),
    NOT_DIGITS_IN_PHONE_NUMBER(103, "Номер телефона должен состоять только из цифр"),
    INVALID_FIRST_DIGIT(104, "Первая цифра номера должна быть 7 или 8"),
    INVALID_EMAIL(105, "Невалидный адрес электронной почты"),
    INVALID_CYRILLIC(106, "Недопустимы символы кроме кириллицы"),
    INVALID_SNILS_CHECKSUM(107, "Невалидный СНИЛС")
}
class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_LENGTH(101, "Недопустимое количество символов"),
    INVALID_NULL_VALUE(102, "Значение не должно быть пустым"),
    NON_DIGITS(103, "Значение должно сотоять только из цифр"),
    INVALID_FIRST_DIGIT(104, "Неверный номер телефона. Номер должен начинаться с цифры 7 или 8"),
    INVALID_EMAIL(105, "Неверный адрес электронной почты"),
    INVALID_CYRILLIC(106, "Недопустимые символы. Разрешена только кириллица"),
    INVALID_SNILS_CHECKSUM(107, "Неверный номер Снился")
}
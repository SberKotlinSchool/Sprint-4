class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимые символы"),
    INVALID_CHARACTER_COUNT(101, "Недопустимое количество символов"),
    INVALID_PHONE_FORMAT(102, "Недопустимый начало телефона"),
    INVALID_EMAIL_FORMAT(103, "Недопустимый формат EMAIL"),
    INVALID_CONTROL_NUMBER(104, "Неверное контрольное число"),
    FIELD_IS_EMPTY(105, "Пустое поле")
}
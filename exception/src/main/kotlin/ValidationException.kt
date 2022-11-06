class ValidationException(val errorCode: Array<String>) : RuntimeException(errorCode.joinToString(","))

enum class ErrorCode(val code: Int, val msg: String) {
    EMPTY_VALUE(10, "Поле не заполнено"),
    INVALID_CHARACTER(11, "Недопустимый символ"),
    NUM_ONLY(12, "Только цифры"),
    CONTROL_VALUE(13, "Контрольное значение не пройдено"),
    EMAIL_NOT_MATCH(14, "Валидация не пройдена @имя_домена"),
    PHONE_NOT_MATCH(15, "Телефон начинается с 7 или 8"),
    MAX_LENGTH(16, "Превышено максимальное кол-во символов"),
    NUMBER_LENGTH(17, "Количество цифр не соответствует заданному"),
}

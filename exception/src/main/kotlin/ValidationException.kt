class ValidationException(val errorCode: Array<String>) : RuntimeException(errorCode.joinToString(","))

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    EMPTY_VALUE(101, "Поле не заполнено"),
    MAX_LENGTH(102, "Превышено максимальное кол-во символов"),
    EMAIL_NOT_MATCH(103, "Валидация не пройдена @имя_домена"),
    CONTROL_VALUE(104, "Контрольное значение не пройдено"),
    NUM_ONLY(105, "Только цифры"),
    PHONE_NOT_MATCH(106, "Телефон начинается с 7 или 8"),
    NUMBER_LENGTH(107, "Количество цифр не соответствует заданному"),
}
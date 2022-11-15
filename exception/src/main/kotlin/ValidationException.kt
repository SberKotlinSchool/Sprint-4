class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_FIRSTNAME(100, "\nИмя: Неверный символ"),
    INVALID_COUNT_CHAR_F(101, "\nИмя: Недопустимое кол-во символов"),

    INVALID_LASTTNAME(102, "\nФамилия: Неверный символ"),
    INVALID_COUNT_CHAR_L(103, "\nФамилия: Недопустимое кол-во символов"),

    INVALID_NUMBER_PHONE(104, "\nТелефон: Недопустимое число"),
    INVALID_START_NUMBER(105, "\nТелефон: Номер должен начинаться с 7 или 8"),
    INVALID_COUNT_CHAR_N(106, "\nТелефон: Недопустимое кол-во символов"),

    INVALID_MAILNAME(107, "\nПочта: Неверный символ"),
    INVALID_DOMEN(108, "\nПочта: Неверная структура, убедитесь в корректности почты"),
    INVALID_COUNT_CHAR_D(109, "\nПочта: Недопустимое кол-во символов"),

    INVALID_SNILS_NUMBER(110, "\nСНИЛС: Недопустимое число"),
    INVALID_COUNT_CHAR_S(111, "\nСНИЛС: Недопустимое кол-во символов"),
    INVALID_CONTROL(112, "\nСНИЛС: Некорректное контрольное число"),

    INVALID_NULL_F(113, "\nИмя: Поле не должно быть пустым"),
    INVALID_NULL_L(114, "\nФамилия: Поле не должно быть пустым"),
    INVALID_NULL_N(115, "\nТелефон: Поле не должно быть пустым"),
    INVALID_NULL_D(116, "\nПочта: Поле не должно быть пустым"),
    INVALID_NULL_S(117, "\nСНИЛС: Поле не должно быть пустым"),

}
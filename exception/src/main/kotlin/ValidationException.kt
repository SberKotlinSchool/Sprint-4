class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE_CHARACTER(100, "Недопустимый символ в номере телефона"),
//    INVALID_FIRST_CHARACTER_IN_PHONE(101, "Номер телефона начинается не с 7 или 8"),
    INVALID_EMAIL(102, "Некорректный формат e-mail"),
    INVALID_CONTROL_SUM_SNILS(103, "Некорректная контрольная сумма СНИЛС"),
    INVALID_CLIENT_FIO(104, "Недопустимые символы в ФИО"),
    INVALID_PHONE_LENGTH(105,  "Длина строки телефона не равна 11"),
    INVALID_FIO_LENGTH(106, "Строка ФИО больше 16 символов"),
    INVALID_EMAIL_LENGTH(106, "Строка email больше 32 символов"),
    INVALID_SNILS_LENGTH(106, "Длина строки СНИЛС не равна 11"),
    INVALID_SNILS_CHARACTER(107, "Недопустимый символ в номере СНИЛС"),
}

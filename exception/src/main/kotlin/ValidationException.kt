class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(", ") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE_CHARACTER(100, "Недопустимый символ или неправильная первая цифра номера"),
    INVALID_PHONE_LENGTH(101, "Недопустимая длина номера"),
    NULL_PHONE(103, "Пустое значение поля телефон"),

    INVALID_NAME_FORMAT(200, "Имя или Фамилия написаны не на Кирилице"),
    INVALID_NAME_LENGTH(201, "Имя или Фамилия не могут состоять более чем из 16 символов"),
    NULL_NAME(203, "Пустое значения поля Имя или Фамилия"),

    INVALID_EMAIL(300, "Неверный формат почты или она не написана на латинице"),
    INVALID_EMAIL_LENGTH(301, "Почта не может состоять больше чем из 32 символов"),
    NULL_EMAIL(303, "Пустое значения поля почта"),

    INVALID_SNILS_FORMAT(400, "Неверный формат СНИЛС"),
    INVALID_SNILS_CHECKSUM(401, "Неверная контрольная сумма СНИЛС"),
    NULL_SNILS(403, "Пустое значения поля СНИЛС"),

    // ...
}
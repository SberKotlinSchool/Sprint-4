class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    FIRST_NAME_NULL_ERROR(101, "Не заполнено имя клиента"),
    FIRST_NAME_NOT_CYRILLIC_ERROR(102, "В имени содержаться недопустимые символы"),
    FIRST_NAME_TOO_LONG_ERROR(103, "Длина имени клиента превышает допустимую"),

    LAST_NAME_NULL_ERROR(104, "Не заполнена фамилия клиента"),
    LAST_NAME_NOT_CYRILLIC_ERROR(105, "В фамилии содержаться недопустимые символы"),
    LAST_NAME_TOO_LONG_ERROR(106, "Длина фамилии клиента превышает допустимую"),

    PHONE_NULL_ERROR(107, "Не заполнен номер телефона"),
    PHONE_NOT_STARTS_FROM_7_OR_8_ERROR(108, "Номер телефона должен начинаться с 7 или 8"),
    PHONE_NOT_CORRECT_LENGTH_ERROR(109, "Номер телефона должен состоять из 11 цифр"),
    PHONE_NOT_NUMBERS_ERROR(110, "Номер телефона содержит недопустимые символы"),

    EMAIL_NULL_ERROR(111, "Не заполнен email"),
    EMAIL_WRONG_FORMAT_ERROR(113, "Email имеет некорректный формат"),
    EMAIL_TOO_LONG_ERROR(114, "Длина email не должна превышать 32 символа"),

    SNILS_NULL_ERROR(115, "Не заполнен СНИЛС"),
    SNILS_NOT_NUMBERS_ERROR(116, "СНИЛС содержит недопустимые символы"),
    SNILS_WRONG_LENGTH_ERROR(117, "СНИЛС должен состоять из 11 цифр"),
    SNILS_CHECKSUM_ERROR(118, "Некорректный номер СНИЛС")
}
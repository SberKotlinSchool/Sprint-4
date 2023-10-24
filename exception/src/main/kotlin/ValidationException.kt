class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(", ") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE_CHARACTER(100, "Недопустимый символ в номере телефона клиента"),
    INVALID_PHONE_START_DIGIT(101, "Номер телефона должен начинаться с 8 или 7"),
    INVALID_PHONE_LENGTH(102, "Длина номера телефона должна составлять 11 символов"),
    PHONE_IS_MISSING(103, "Телефон клиента не заполнен"),
    MAIL_DOMAIN_IS_MISSING(104, "В адресе электронной почты отсутствует домен"),
    INVALID_MAIL_LENGTH(105, "Длина адреса электронной почты должна составлять не более 32 символа"),
    INVALID_MAIL_LANGUAGE(106, "Адрес электронной почты написан не на латинице"),
    MAIL_ADDRESS_IS_MISSING(107, "Почта клиента не заполнена"),
    INVALID_SNILS_LENGTH(108, "Длина СНИЛС должна составлять 11 символов"),
    INVALID_SNILS_CONTROL_NUMBER(109, "Невалидная контрольное число СНИЛС"),
    INVALID_SNILS_CHARACTER(110, "Недопустимый символ в СНИЛС"),
    SNILS_IS_MISSING(111, "СНИЛС клиента не заполнен"),
    INVALID_NAME_LANGUAGE(112, "Имя или фамилия клиента написаны не на кириллице"),
    INVALID_NAME_LENGTH(113, "Длина имени и фамилии клиента должна составлять не более 16 символов"),
    NAME_IS_MISSING(114, "Имя или фамилия клиента не заполнены");
}
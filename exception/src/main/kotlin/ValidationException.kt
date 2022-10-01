class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(", ") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_NAME_CHARACTER(101, "Недопустимый символ в имени (допустима только кириллица)"),
    INVALID_NAME_LENGTH(102, "Превышена длина имени (должно быть не более 16 символов)"),
    EMPTY_NAME(103, "Имя должно быть заполнено"),

    INVALID_SURNAME_CHARACTER(201, "Недопустимый символ в фамилии (допустима только кириллица)"),
    INVALID_SURNAME_LENGTH(202, "Превышена длина фамилии (должно быть не более 16 символов)"),
    EMPTY_SURNAME(203, "Фамилия должна быть заполнена "),

    INVALID_PHONE_CHARACTER(301, "Недопустимый символ в телефоне (должен начинаться с 7 или 8, только кириллиица)"),
    INVALID_PHONE_LENGTH(302, "Неверная длина номера телефона (допустимая длина 11 знаков)"),
    EMPTY_PHONE(303, "Номер телефона должен быть заполнен"),

    INVALID_EMAIL_CHARACTER(401, "Недопустимый символ в Email"),
    INVALID_EMAIL_LENGTH(402, "Превышена длина Email (допустимая длина не более 32 знаков)"),
    EMPTY_EMAIL(403, "Email должен быть заполнен"),

    INVALID_SNILS_CHARACTER(501, "Недопустимый символ в СНИЛС (допустимы только цифры)"),
    INVALID_SNILS_CHECKSUM(502, "Неверное контрольное число СНИЛС"),
    EMPTY_SNILS(503, "СНИЛС должен быть заполнен"),
    INVALID_SNILS_LENGTH(504, "Неверная длина СНИЛС (допустимая длина 11 знаков)"),
}
class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    VALUE_IS_NULL(101, "Имя, фамилия, номер телефона, email и снилс не могут иметь занчение NULL"),
    INVALID_NAME_LENGTH(102, "Длина имени и фамилии должны не превышать 16 символов"),
    INVALID_NAME_LANGUAGE(103, "Имя и фамилия должны быть на кирилице"),
    INVALID_REGION(104, "Номер телефона должен начинаться с +7 или 8"),
    INVALID_PHONE_NUMS(105, "Номер телефона должен состоять из цифр"),
    INVALID_PHONE_LENGTH(106, "Длина номера должна быть равна 11 знакам"),
    INVALID_EMAIL_LANGUAGE(107, "Email должен состоять из латинских  букв"),
    INVALID_EMAIL_DOMEN(108, "В email нет домена"),
    INVALID_EMAIL_LENGTH(109, "Длина email не должна превышать 32 символа"),
    INVALID_SNILS_CHARACTER(110, "Недопустимый символ для снилс"),
    INVALID_SNILS_LENGTH(111, "Длина снилс должна быть равна 11 символов"),
    INVALID_SNILS_CONTROL_NUMBER(112, "Контрольное число не прошло валидацию"),
}
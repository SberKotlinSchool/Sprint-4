class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER_IN_NAME(100, "Недопустимый символ, в поле только кириллица") ,
    INVALID_LENGTH_IN_NAME(101, "Некорректная длина, не более 16 символов") ,
    INVALID_PHONE_NUMBER(102, "Номер телефона должен начинаться с 7 или 8") ,
    INVALID_CHARACTER_IN_PHONE_NUMBER(103, "Недопустимый символ, в поле только цифры") ,
    INVALID_LENGTH_IN_PHONE_NUMBER(104, "Некорректная длина, должно быть ровно 11 символов") ,
    INVALID_CHARACTER_IN_EMAIL(105, "Недопустимый символ, в поле только латиница") ,
    INVALID_LENGTH_IN_EMAIL(106, "Некорректная длина, в поле не более 32 символов"),
    INVALID_DOMAIN_IN_EMAIL(107, "В поле должно быть @имя_домена"),
    INVALID_CHARACTER_IN_SNILS(108, "Недопустимый символ, в поле только цифры") ,
    INVALID_LENGTH_IN_SNILS(109, "Некорректная длина, должно быть ровно 11 символов") ,
    INVALID_SNILS(110, "Некорректный СНИЛС, нет соответствия контрольной сумме")

}
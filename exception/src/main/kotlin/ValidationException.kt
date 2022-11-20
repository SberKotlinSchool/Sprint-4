class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(101, "Недопустимая длина"),
    INVALID_PHONE_NUMBER(200, "Некорректный номер телефона, должен  содержать только цифры и начинаться с 7 или 8"),
    INVALID_PHONE_LENGTH(201, "Недопустимая длина номера телефона"),
    INVALID_EMAIL(301, "Недопустимый e-mail, поле должно содержать @имя_домена, латинские  символы, длина поля не более 32 символов"),
    INVALID_SNILS(400, "Некорректный СНИЛС, должен  содержать 11 цифр"),
    INVALID_SNILS_CHECKSUM(401, "Валидация контрольного числа  СНИЛС не прошла успешно")

}
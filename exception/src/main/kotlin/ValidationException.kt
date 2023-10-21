class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_LAST_NAME(11, "Поле не более 16 символов, допустима только кириллица"),
    NO_LAST_NAME(12, "Фамилие отсутствует"),
    INVALID_FIRST_NAME(21, "Поле не более 16 символов, допустима только кириллица"),
    NO_FIRST_NAME(22, "Имя отсутствует"),
    INVALID_PHONE_NUMBER(31, "Поле должно содержать только цифры, длина 11 символов, начинаться с 7 или 8"),
    NO_PHONE_NUMBER(32,"Телефон отсутствует"),
    INVALID_EMAIL(41, "Поле не более 32 символов, только латиница, наличие @имя_домена"),
    NO_EMAIL(42, "Email отсутствует"),
    INVALID_SNILS(51, "Длина поля должна быть 11 символов"),
    INVALID_SNILS_CHECK_DIGIT(52, "Ошибка проверки контрольного числа"),
    NO_SNILS(53, "СНИЛС отсутствует")
}
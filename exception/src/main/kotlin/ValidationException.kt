class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LAST_NAME(101, "В поле допустимо не более 16 символов, допустима только кириллица"),
    NO_LAST_NAME(201, "Фамилие отсутствует"),
    INVALID_FIRST_NAME(102, "В поле допустимо не более 16 символов, допустима только кириллица"),
    NO_FIRST_NAME(202, "Имя отсутствует"),
    INVALID_PHONE_NUMBER(103, "В поле допустимо содержать только цифры, длина 11 символов, начинаться с 7 или 8"),
    NO_PHONE(203,"Номер телефон отсутствует"),
    INVALID_EMAIL(104, "В поле допустимо не более 32 символов, только латиница, наличие @имя_домена"),
    NO_EMAIL(204, "Email отсутствует"),
    INVALID_SNILS(105, "В поле допустимо 11 символов"),
    NO_SNILS(205, "СНИЛС отсутствует")
}

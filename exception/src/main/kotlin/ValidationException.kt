class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    PHONE_IS_NULL(101, "Телефонный номер не задан"),
    INVALID_FIRST_CHARACTER_OF_PHONE(102, "Телефонный номер должен начинаться с 7 или 8"),
    INVALID_PHONE_SIZE(103, "Недопустимый размер номера телефона, должна быть равна 11"),
    NAME_IS_NULL(104, "Имя не задано"),
    LAST_NAME_IS_NULL(105, "Фамилия не задана"),
    INVALID_FIRST_NAME_SIZE(106, "Недопустимый размер поля 'Имя', длина должна быть не более 16 символов"),
    INVALID_CHARACTER_IN_FIRST_NAME(107, "Недопустимый символ в поле 'Имя'"),
    INVALID_LAST_NAME_SIZE(108, "Недопустимый размер поля 'Фамилия', длина должна быть не более 16 символов"),
    INVALID_CHARACTER_IN_LAST_NAME(109, "Недопустимый символ в поле 'Фамилия'"),
    INVALID_CHARACTER_IN_EMAIL(110, "Недопустимый символ в поле 'Email'"),
    INVALID_EMAIL_FORMAT(111, "Недопустимый формат поля 'Email'"),
    INVALID_EMAIL_SIZE(112, "Недопустимый размер поля 'Email', длина должна быть не более 32 символов"),
    EMAIL_IS_NULL(113, "Email не задан"),
    INVALID_CHARACTER_IN_SNILS(114, "Недопустимый символ в поле 'СНИЛС'"),
    INVALID_SNILS(11514, "Не валидное поле 'СНИЛС'"),
    INVALID_SNILS_SIZE(114, "Недопустимый размер поля 'СНИЛС', длина должна быть не более 11 символов"),
    SNILS_IS_NULL(115, "СНИЛС не задан"),
}

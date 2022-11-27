class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    MISSING_PHONE(110, "Не заполнено обязательное поле: номер телефона"),
    INVALID_PHONE_FORMAT(111, "Номер телефон должен состоять из 11 цифр и начинаться с 7 или 8"),

    MISSING_FIRST_NAME(121, "Не заполнено обязательное поле: имя"),
    INVALID_FIRST_NAME_FORMAT(120, "Имя должно содержать только кириллицу, не более 16 символов"),

    MISSING_LAST_NAME(130, "Не заполнено обязательное поле: фамилия"),
    INVALID_LAST_NAME_FORMAT(131, "Фамилия должна содержать только кириллицу, не более 16 символов"),

    MISSING_EMAIL(140, "Не заполнено обязательное поле: емейл"),
    INVALID_EMAIL_FORMAT(
        141,
        "Емейл должен состоять из латинских букв, не более 16 символов, а также иметь @имя_домена"
    ),

    MISSING_SNILS(150, "Не заполнено обязательное поле: емейл"),
    INVALID_SNILS_FORMAT(
        151,
        "Емейл должен состоять из латинских букв, не более 16 символов, а также иметь @имя_домена"
    ),
    INVALID_SNILS_CHECKSUM(152, "Емейл содержит некорректное контрольное число")
}
class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    /**
     * Common
     */
    INVALID_CHARACTER(-99, "Недопустимый символ"),
    EMPTY(-100, "Поле не может быть пустым"),

    /**
     * Name
     */
    NAME_EXCEEDS_LENGTH(-101, "Имя превышает 16 символов"),

    /**
     * Phone
     */
    PHONE_INVALID_LENGTH(-102, "Телефон должен состоять из 11 символов"),
    PHONE_STARTS_WITH_INVALID_CHARACTER(-103, "Телефон должен начинаться с 7 или 8"),
    /**
     * Email
     */
    EMAIL_EXCEEDS_LENGTH(-104, "Email превышает длину 32 символа"),
    EMAIL_INVALID_DOMAIN(-105, "Email должен содержать название домена после знака @ в латинице"),
    EMAIL_INVALID_NAME(-106, "Email должен содержать только латиницу"),
    /**
     * SNILS
     */
    SNILS_INVALID_LENGTH(-107, "CНИЛС должен состоять из 11 символов"),
    SNILS_INVALID_CHECK_SUM(-108, "Неправильное контрольное число CНИЛС"),
}

class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.distinct().joinToString(", ") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE_NUMBER(100, "Неверный формат телефонного номера. Номер должен начинаться с 7 или 8ки, только цифры, длина 11 знаков"),
    INVALID_EMAIL(200, "Неверный email. Email должен состоять из латинских букв, в нем должно присутствовать @имя_домена, " +
            "длина не более 32 символов"),
    INVALID_NAME(300, "Фамилия или имя заполнены неверно. Поле заполняется кириллицей, не более 16 символов"),
    INVALID_SNILS(400, "Неверный формат СНИЛС. Номер должен состоять только из цифры, длина 11 символов."),
    INVALID_SNILS_CHECK_SUM(500, "СНИЛС не соответствует контрольному числу")
}
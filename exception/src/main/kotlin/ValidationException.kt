class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",\n") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_FORMAT_NAME(100, "Имя - только кириллица, не более 16 символов каждое поле"),
    INVALID_FORMAT_PHONE(101, "Телефон - начинается с 7 или 8ки, только цифры, 11 знаков"),
    INVALID_FORMAT_EMAIL(102, "Емайл - латиница, с валидацией @имя_домена, не более 32 символов"),
    INVALID_FORMAT_SNILS(103, "СНИЛС - только цифры, 11 символов"),
    INVALID_FORMAT_SNILS_SUM(104, "СНИЛС - Ошибка валидации контрольного числа"),
    STRING_NULL_OR_BLANK(105, "Строка пустая или равна null"),
}
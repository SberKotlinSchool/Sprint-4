class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_FIRST_NAME(101, "Недопустимое имя (имя - только кириллица, не более 16 символов)"),
    INVALID_LAST_NAME(102, "Недопустимая фамилия (фамилия - только кириллица, не более 16 символов)"),
    INVALID_PHONE(103, "Недопустимый номер телефона (телефон - начинается с 7 или 8ки, только цифры, 11 знаков)"),
    INVALID_EMAIL(104, "Недопустимый Email (Email - латиница, формат: локальное_имя@имя_домена, не более 32 символов)"),
    INVALID_SNILS(105, "Недопустимый СНИЛС (СНИЛС - только цифры, 11 символов)")
}
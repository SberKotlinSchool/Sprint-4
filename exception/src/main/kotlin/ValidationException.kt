class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",\n") { it.msg })

enum class ErrorCode(val code: Int, var msg: String) {
    INVALID_NAME(101, "Имя и Фамилия - только кириллица, не более 16 символов каждое поле"),
    INVALID_PHONE(102, "Телефон - начинается с 7 или 8ки, только цифры, 11 знаков"),
    INVALID_EMAIL(103, "Email - латиница, с валидацией @имя_домена, не более 32 символов"),
    INVALID_SNILS(104, "СНИЛС - только цифры, 11 символов, с валидацией Контрольного числа"),
    INVALID_NULL_OR_EMPTY(105, "Поле не должно быть пустым");
}
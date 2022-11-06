class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String)
{
    INVALID_FIRST_NAME(100, "Имя не соответсвуте формату: только кириллица, не более 16 символов"),
    INVALID_LAST_NAME(200, "Фамилия не соответсвуте формату: только кириллица, не более 16 символов"),
    INVALID_PHONE(300, "Телефон не соответсвуте формату: начинается с 7 или 8, только цифры, 11 знаков"),
    INVALID_EMAIL(400, "Email не соответсвуте формату: латиница, с валидацией @имя_домена, не более 32 символов"),
    INVALID_SNILS(500, "СНИЛС не соответсвуте формату: только цифры, 11 символов, с валидацией Контрольного числа")
}
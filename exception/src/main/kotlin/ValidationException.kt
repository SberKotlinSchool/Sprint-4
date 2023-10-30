class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Данные содержат символы, отличные от кириллицы"),
    INVALID_NAME_LENGTH(101,"Длина имени или фамилии > 16 символов"),
    INVALID_PHONE_PREFIX(102,"Номер начинается не с 7 или 8"),
    INVALID_PHONE_LENGTH(103,"Длина телефона > 11 символов"),
    INVALID_EMAIL_LENGTH(104,"Длина Email > 32 символов"),
    INVALID_EMAIL_DOMAIN(105,"Некорректный домен почты"),
    INVALID_SNILS_LENGTH(106,"Длина СНИЛС > 11 символов"),
    PARAMETER_IS_NULL(107,"Один или несколько параметров объекта не содержат данные")
}
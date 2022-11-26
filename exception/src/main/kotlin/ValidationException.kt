class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH_LN(101,"Длина Фамилии более 16"),
    INVALID_LENGTH_FN(102,"Длина Имени более 16"),
    INVALID_LENGTH_SNILS(103,"Длина СНИЛС более 11"),
    INVALID_LENGTH_EMAIL(104,"Длина Email более 32"),
    INVALID_START_CHAR_PHONE(105,"Номер начинается не с (7, 8)"),
    INVALID_LENGTH_PHONE(106,"Длина телефона более 11"),
    INVALID_OBJECT(107, "Объект из null"),
    INVALID_DOMAIN(108,"Некорректный домен почты"),
    SUM_SNILS_ERROR(109,"Ошибка контрольной суммы СНИЛС")
    // ...
}
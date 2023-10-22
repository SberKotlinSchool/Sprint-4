class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ. Используйте латинские символы."),
    INVALID_NUMBER(101, "Номер должен начинаться с 7-ки или 8-ки"),
    INVALID_LENGTH_NUMBER(101, "Длинна номера телефона должна быть 11 символов"),
    INVALID_EMAIL_FORMAT(103, "Введенный email не соответствует заданному формату - @имя_домена"),
    INVALID_EMAIL_LENGTH(104, "Допустимая длинна email 32 символа"),
    INVALID_LANGUAGE_EMAIL(105, "В email допустимы только латинские символы"),
    INVALID_CYRILLIC_CHARACTERS(106, "Допустимы только символы из кириллицы"),
    INVALID_PERSON_DATA_LENGTH(107, "Длинна имя или фамилии должна быть менее 16 символов"),
    INVALID_LENGTH_SNILS(108, "Длинна СНИЛС должна быть равна 11"),
    INVALID_CONTROL_SUM_SNILS(109, "Введен некорректный СНИЛС, не совпали контрольные суммы"),
    INVALID_DATA(0, "Поле не может быть пустым")
}
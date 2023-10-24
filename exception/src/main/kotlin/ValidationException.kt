class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE_NUMBER(101, "Номер должен начинаться с 7-ки или 8-ки"),
    INVALID_PHONE_LENGTH(102, "Длинна номера телефона должна быть 11 символов"),
    INVALID_EMAIL(303, "Введенный email не соответствует заданному формату - @имя_домена. Только на латинице."),
    INVALID_EMAIL_LENGTH(304, "Допустимая длинна email 32 символа"),
    INVALID_NAME_LANGUAGE(206, "Имя и фамилия должны быть на латинице"),
    INVALID_NAME_LENGTH(207, "Длинна имя или фамилии должна быть менее 16 символов"),
    INVALID_LENGTH_SNILS(408, "Длинна СНИЛС должна быть равна 11"),
    INVALID_CONTROL_SUM_SNILS(409, "Введен некорректный СНИЛС, не совпали контрольные суммы"),
    INVALID_DATA(0, "Поле не может быть пустым")
}
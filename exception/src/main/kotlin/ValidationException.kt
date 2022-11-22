class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    //INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_SNILS_NUMBER(102,"Недопустимый номер СНИЛС"),
    INVALID_EMAIL(103,"Недопустимый e-mail"),
    INVALID_NAME(104,"Недопустимый символ в имени клиента"),
    INVALID_SURNAME(105,"Недопустимый символ в фамилии клиента"),
    INVALID_PHONE_NUMBER(101,"Недопустимый номер телефона")
}
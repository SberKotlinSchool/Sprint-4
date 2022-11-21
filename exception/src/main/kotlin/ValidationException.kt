class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    LAST_NAME_IS_EMPTY(101, "Не указана фамилия"),
    LAST_NAME_INVALID_CHARACTER(102, "Недопустимый символ в фамилии"),
    FIRST_NAME_IS_EMPTY(103, "Не указана фамилия"),
    FIRST_NAME_INVALID_CHARACTER(104, "Недопустимый символ в фамилии"),
    INVALID_PHONE_NUMBER(105, "Неверно указан номер телефона"),
    INVALID_EMAIL(106, "Неверно указан адрес электронной почты"),
    INVALID_SNILS(107, "Неверно указан СНИЛС")
}
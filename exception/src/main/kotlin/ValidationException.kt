class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    EMPTY(100, "Пустой ввод"),
    LAST_NAME_INVALID_CHARACTER(200, "Недопустимый символ"),
    FIRST_NAME_INVALID_CHARACTER(300, "Недопустимый символ в имени"),
    INVALID_PHONE_NUMBER(400, "Неверно указан номер телефона"),
    INVALID_EMAIL(500, "Неверно указан адрес электронной почты"),
    INVALID_SNILS(600, "Неверно указан СНИЛС")
}
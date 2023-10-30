class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_FIRST_NAME(101, "Недопустимый символ в имени"),
    INVALID_LAST_NAME(102, "Недопустимый символ в фамилии"),
    INVALID_PHONE_NUMBER(103, "Некорректный номер телефона"),
    INVALID_EMAIL(104, "Некорректный адрес почты"),
    INVALID_SNILS(105, "Некорректный номер СНИЛС"),
    EMPTY_FIELD(106, "Поле не заполнено"),
    INVALID_COUNT_CHARACTER(107, "Недопустимое количество символов")
}
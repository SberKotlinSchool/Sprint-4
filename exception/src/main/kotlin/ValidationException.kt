class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ")
    , VALUE_IS_EMPTY(101, "Полученное значение NULL или пустая строка")
    , INCORRECT_PHONE_NUMBER (102, "Некорректный номер телефона")
    , INCORRECT_FIRST_NAME (103, "Некорректное имя")
    , INCORRECT_LAST_NAME (104, "Некорректная фамилия")
    , INCORRECT_EMAIL (105, "Некорректный e-mail")
    , INCORRECT_SNILS (106, "Некорректный СНИЛС")
}
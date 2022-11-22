class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    EMPTY_INPUT(100, "Пустая строка"),
    INVALID_FIRSTNAME(101, "Ошибка валидации имени"),
    INVALID_LASTNAME(102, "Ошибка валидации фамилии"),
    INVALID_PHONE(103, "Ошибка валидации номера телефона"),
    INVALID_EMAIL(104, "Ошибка валидации электронной почты"),
    INVALID_SNILS(105, "Ошибка валидации СНИЛС"),
}
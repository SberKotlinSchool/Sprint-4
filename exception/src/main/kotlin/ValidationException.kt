class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER_PHONE(100, "Недопустимый символ в телефонном номере"),
    INVALID_LENGTH_PHONE(101, "Недопустимая длина телефонного номера"),
    INVALID_CHARACTER_EMAIL(102, "Недопустимый символ в e-mail"),
    INVALID_LENGTH_EMAIL(103, "Недопустимая длина e-mail"),
    INVALID_CHARACTER_FIRST_NAME(104, "Недопустимый символ в имени"),
    INVALID_LENGTH_FIRST_NAME(105, "Недопустимая длина имени"),
    INVALID_CHARACTER_LAST_NAME(106, "Недопустимый символ в фамилии"),
    INVALID_LENGTH_LAST_NAME(107, "Недопустимая длина фамилии"),
    INVALID_CHARACTER_SNILS(108, "Недопустимый символ в СНИЛС"),
    INVALID_LENGTH_SNILS(109, "Недопустимая длина СНИЛС"),
    INVALID_CHECK_NUMBER_SNILS(110, "Недопустимое контрольное число в СНИЛС")
}
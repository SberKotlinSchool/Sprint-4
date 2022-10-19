class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_FIO_FORMAT(0, "Некорректный формат ФИО"),
    INVALID_PHONE_FORMAT(1, "Некорректный формат номера телефона"),
    INVALID_EMAIL_FORMAT(2,"Некорректный формат электронной почты"),
    INVALID_SNILS_FORMAT(3, "Некорректный формат СНИЛС")
}
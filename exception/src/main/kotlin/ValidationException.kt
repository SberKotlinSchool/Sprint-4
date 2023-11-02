class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(", ") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    PHONE_IS_MISSING(105, "Не заполнен номер телефона"),
    MAIL_IS_MISSING(106, "Не заполнен email"),
    FIRST_NAME_IS_MISSING(107, "Не заполнено имя"),
    LAST_NAME_IS_MISSING(108, "Не заполнена фамилия"),
    SNILS_IS_MISSING(109, "Не заполнен СНИЛС"),
    INVALID_PHONE(110, "Некорректный номер телефона"),
    INVALID_FIRST_NAME(111, "Некорректное имя"),
    INVALID_LAST_NAME(111, "Некорректное имя"),
    INVALID_MAIL(112, "Некорректный email"),
    INVALID_SNILS(113, "Некорректный СНИЛС"),
    INVALID_SNILS_SUM(114, "Некорректная контрольная сумма СНИЛС"),
}
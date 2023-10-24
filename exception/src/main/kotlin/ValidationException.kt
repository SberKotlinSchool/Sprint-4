class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE(100, "Невалидный номер телефона"),
    INVALID_NAME(101, "Невалидное имя"),
    INVALID_EMAIL(102, "Невалидный email"),
    INVALID_SNILS(103, "Невалидный СНИЛС")
}

fun Array<ErrorCode>.toString(): String {
    return this.joinToString(",")
}

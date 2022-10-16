class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val msg: String) {
    INVALID_CHARACTER("Недопустимый символ"),
    LENGTH("Длина не соответствует ожидаемой"),
    DOMAIN("Нет имени домена"),
    EMPTY_INPUT("Пустое поле"),
    SNILS_CHECK_NUMBER("Неправильное контрольное число СНИЛС")
}
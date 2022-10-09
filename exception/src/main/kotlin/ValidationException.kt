class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    EMPTY(0, "Пусто"),

    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_CHARACTER_NAMEorSURNAME(101, "Недопустимый символ"),
    INVALID_CHARACTER_PHONE(103, "Недопустимый символ"),
    INVALID_CHARACTER_EMAIL(104, "Недопустимый символ"),
    INVALID_CHARACTER_SNILS(105, "Недопустимый символ"),

    INVALID_LENGTH_NAME_OR_SURNAME(200, "Превышена максимальная длина"),
    INVALID_LENGTH_EMAIL(203, "Слишком длинное имя"),
    INVALID_LENGTH_SNILS(204, "Слишком длинное имя"),
    INVALID_LENGTH_PHONE(205, "Слишком длинное имя"),

    INVALID_EMAIL_PATTERN(301, "Мыло не соотвествеует шаблону @имя_домена"),
    INVALID_PHONE_COUNTRY(302, "Только телефоны с кодом страны Россия"),
    INVALID_SNILS_CHECKBIT(303, "Проверочный код снил не хсодиться")

}
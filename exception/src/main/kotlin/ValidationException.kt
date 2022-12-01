class ValidationException(val errorCode: Array<ErrorCode>) :
    RuntimeException(errorCode.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    NULL_OR_EMPTY_PHONE(10, "Отсутвуют данные о телефонном номере"),
    INVALID_CHARACTER_IN_PHONE(11,
        "Нарушено условие для телефонного номера: начинается с 7 или 8ки, только цифры, 11 знаков"),

    NULL_OR_EMPTY_EMAIL(20, "Отсутвуют данные об электронном адресе"),
    INVALID_CHARACTER_IN_EMAIL(21,
        "Нарушено условие для телефонного номера: латиница, с валидацией @имя_домена, не более 32 символов "),

    NULL_OR_EMPTY_SNILS(30, "Отсутвуют данные об СНИЛС"),
    INVALID_CHARACTER_IN_SNILS(31,
        "Нарушено условие для СНИЛС: только цифры, 11 символов, с валидацией Контрольного числа"),

    NULL_OR_EMPTY_NAME(40, "Отсутвуют данные об имени или фамилии"),
    INVALID_CHARACTER_IN_NAME(41, "Нарушено условие для имени: только кириллица, не более 16 символов каждое поле")
}
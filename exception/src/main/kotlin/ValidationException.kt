class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

//Имя и Фамилия - только кириллица, не более 16 символов каждое поле
//Телефон - начинается с 7 или 8ки, только цифры, 11 знаков
//Email - латиница, с валидацией @имя_домена, не более 32 символов
//СНИЛС - только цифры, 11 символов, с валидацией Контрольного числа

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER_FIO_SIZE(100, "Размер поля Фамилия/Имя должен быть не более 16 символов"),
    INVALID_CHARACTER_IN_FIO(101, "Недопустимый символ в поле Фамилия/Имя "),
    NAME_OR_LASTNAME_IS_NULL(102, "Поле Фамилия/имя не заполнено"),
    PHONENUMBER_IS_NULL(103, "Поле телефон не заполнено"),
    INVALID_CHARACTER_IN_PHONENUMBER(105, "Недопустимый символ в поле телефон"),
    INVALID_PHONENUMBER_SIZE(106, "Размер поля Телефон должен быть 11 знаков"),
    EMAIL_IS_NULL(107, "Поле Email не заполнено"),
    INVALID_CHARACTER_IN_EMAIL(108, "Недопустимый символ в поле Email"),
    INVALID_EMAIL(109, "Неккоретный формат Email"),
    INVALID_EMAIL_SIZE(110, "Размер поля Email должен быть не более 32 символов"),
    SNILS_IS_NULL(111, "Поле СНИЛС не заполнено"),
    INVALID_CHARACTER_SNILS(112, "Недопустимый символ в поле СНИЛС"),
    INVALID_SNILS_SIZE(113, "Размер поля СНИЛС должен быть 11 символов"),
    INVALID_SNILS(114, "СНИЛС не валиден")

}

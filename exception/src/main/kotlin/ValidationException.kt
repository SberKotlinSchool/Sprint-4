class ValidationException(val errorCode: Array<ErrorCode>) : RuntimeException(errorCode.joinToString(",") { it.msg })

//Имя и Фамилия - только кириллица, не более 16 символов каждое поле
//Телефон - начинается с 7 или 8ки, только цифры, 11 знаков
//Email - латиница, с валидацией @имя_домена, не более 32 символов
//СНИЛС - только цифры, 11 символов, с валидацией Контрольного числа
enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTER(100, "Недопустимый символ"),
    INVALID_LENGTH(101, "Недопустимая длина"),
    INVALID_PHONE_NUMBER(200, "Некорректный номер телефона, должен  содержать только цифры и начинаться с 7 или 8"),
    INVALID_PHONE_LENGTH(201, "Недопустимая длина")

// ...
}
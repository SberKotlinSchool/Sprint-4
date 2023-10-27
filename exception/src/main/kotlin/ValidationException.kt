open class ValidationException(val errorCodes: Array<ErrorCode>) : RuntimeException(errorCodes.joinToString(",") { it.msg })

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_PHONE(100, "Телефон должен начинаться с 7ки или 8ки и содержать всего 11 цифр"),
    INVALID_NAME(101, "Поле должно содержать не более 16 символов и только кириллицу"),
    INVALID_EMAIL(102, "Почта должна содержать не более 32 латинских символов и иметь шаблон ник@домен.зона"),
    INVALID_SNILS(103, "СНИЛС должен состоять из 11 цифр с валидным контрольным числом")
}
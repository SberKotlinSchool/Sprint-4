class ValidationException(typesAndErrors: Map<String, List<Error>>) :
    RuntimeException("\n" + typesAndErrors.entries.joinToString(separator = "\n") { entry ->
        "${entry.key}: ${ entry.value.joinToString(separator = ";  ") { it.toString() } }"
    } + "\n")

abstract class Error(open val value: Any?, open val constraint: Any?, val code: ErrorCode) { }

class NumberError(
    override val value: Number, override val constraint: Number, code: ErrorCode) : Error(value, constraint, code) {

    override fun toString(): String {
        return "\n    Constraint Violation! Code: ${code.code} - '${code.msg}'; value: '$value'; constraint: '$constraint'"
    }
}

class NotParametrizedError(code: ErrorCode) : Error(value = null, constraint = null, code) {
    override fun toString(): String {
        return "\n    Constraint Violation! Code: ${code.code} - '${code.msg}'"
    }
}

enum class ErrorCode(val code: Int, val msg: String) {
    INVALID_CHARACTERS(100, "Недопустимые символы"), // 1xx - Common Errors
    LENGTH_TOO_SHORT(101, "Длина меньше допустимого значения"),
    LENGTH_TOO_LONG(102, "Длина больше допустимого значения"),
    LENGTH_NOT_EQUAL(103, "Длина должна быть равна значению"),
    PHONE_WRONG_FIRST_NUMBER(200, "Некорректная первая цифра номера (не 7 или 8)"), // 2xx - Phone Errors
    NAME_WRONG_FIRST_LETTER(300, "Некорректная первая буква (прописная или запрещенный символ)"), // 3xx - Name Surname Errors
    EMAIL_FORMAT(400, "Некорректный формат email (прописная латиница и формат 'abcd@efgh.klm')"), // 4xx - Email Errors
    SNILS_CONTROL_SUM(500, "Контрольная сумма СНИЛС неверна"), // 5xx - Snils Errors

}
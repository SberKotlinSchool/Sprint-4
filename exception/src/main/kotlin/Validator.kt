import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

//Имя и Фамилия - только кириллица, не более 16 символов каждое поле
//Телефон - начинается с 7 или 8ки, только цифры, 11 знаков
//Email - латиница, с валидацией @имя_домена, не более 32 символов
//СНИЛС - только цифры, 11 символов, с валидацией Контрольного числа
class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        value?.let {
            require(value.startsWith("7") || value.startsWith("8")) { return listOf(ErrorCode.INVALID_PHONE_NUMBER) }
            require(isNumeric(value)) { return listOf(ErrorCode.INVALID_PHONE_NUMBER) }
            require(value.length == 11) { return listOf(ErrorCode.INVALID_PHONE_LENGTH) }
        }
        return emptyList()
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        value?.let {
            require(value.length <= 16) { return listOf(ErrorCode.INVALID_LENGTH) }
            require(Pattern.matches(".*\\p{InCyrillic}.*", value)) { return listOf(ErrorCode.INVALID_CHARACTER) }
        }
        return emptyList()
    }
}

private fun isEmailValid(email: String): Boolean {
    return Pattern.compile(
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
    ).matcher(email).matches()
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?) = value?.let {
        when {
            value.length > 32 -> listOf(ErrorCode.INVALID_EMAIL)
            !isEmailValid(value) -> listOf(ErrorCode.INVALID_EMAIL)
            else -> emptyList()
        }
    } ?: emptyList()

}

class SnilsValidator : Validator<String>() {

    override fun validate(value: String?) = value?.let {
        when {
            value.length != 11 -> listOf(ErrorCode.INVALID_SNILS)
            !isNumeric(value) -> listOf(ErrorCode.INVALID_SNILS)
            !isCheckSumOk(value) -> listOf(ErrorCode.INVALID_SNILS_CHECKSUM)
            else -> emptyList()
        }
    } ?: emptyList()

}

fun isNumeric(strNum: String?) = strNum?.toLongOrNull() != null

fun isCheckSumOk(snils: String?) = snils?.let {
    var checkSum = 0
    for (j in 0..8) {
        val toInt = snils[j].digitToIntOrNull() ?: return false
        val i = toInt * (9 - j)
        checkSum += i
    }
    checkSum == snils.substring(9, 11).toInt()
} ?: false



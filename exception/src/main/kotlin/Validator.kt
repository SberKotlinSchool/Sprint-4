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
            require(value.startsWith("7")|| value.startsWith("8")) {return listOf(ErrorCode.INVALID_PHONE_NUMBER) }
            require(isNumeric(value)){return listOf(ErrorCode.INVALID_PHONE_NUMBER)}
            require(value.length <= 11) { return listOf(ErrorCode.INVALID_PHONE_LENGTH) }
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

fun isNumeric(strNum: String?): Boolean {
    if (strNum == null) {
        return false
    }
    try {
        strNum.toInt()
    } catch (nfe: NumberFormatException) {
        return false
    }
    return true
}
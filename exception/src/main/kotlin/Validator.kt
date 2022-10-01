import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
            return if(value != null && (value.length != 11 || !(value.startsWith("7") || value.startsWith("8")) ||  !value.onlyDigit())){
                listOf(ErrorCode.INVALID_PHONE_NUMBER)
            } else emptyList()
        }
    }

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if(value != null && (value.length > 16 || !value.onlyCyrillic())){
            listOf(ErrorCode.INVALID_FIRST_NAME)
        } else emptyList()
    }
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if(value != null && (value.length > 16 || !value.onlyCyrillic())){
            listOf(ErrorCode.INVALID_LAST_NAME)
        } else emptyList()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if(value != null && (value.length > 32 || !value.onlyLatin() || !value.isEmail())){
            listOf(ErrorCode.INVALID_EMAIL)
        } else emptyList()
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return if(value != null && (value.length != 11 || !value.onlyDigit() || !value.checkSnils())){
            listOf(ErrorCode.INVALID_SNILS)
        } else emptyList()
    }
}

fun String.onlyDigit(): Boolean {
    this.forEach { if (!it.isDigit()) return false }
    return true
}

fun String.onlyCyrillic(): Boolean {
    this.forEach { if( it !in 'А'..'Я' && it !in 'а'..'я' ) return false }
    return true
}

fun String.onlyLatin(): Boolean {
    this.filter { it.isLetter()}.forEach { if( it !in 'A'..'Z' && it !in 'a'..'z' ) return false }
    return true
}

fun String.isEmail(): Boolean {
    return EMAIL_ADDRESS_PATTERN.matcher(this).matches()
}

val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
)

fun String.checkSnils(): Boolean {
    val numberSnils = this.substring(0, 9)
    val checkSum = this.substring(9)
    var sum =0
    var count = 9
    for (i in numberSnils.indices){
        sum += numberSnils[i].toString().toInt() * count
        count -= 1
    }
    return sum % 101 % 100 == checkSum.toString().toInt()

}
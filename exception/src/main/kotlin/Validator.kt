import kotlin.math.E

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regexPhone = Regex("^[7,8]\\d{2,10}\$")
        val listErrors = ArrayList<ErrorCode>()
        if (value.isNullOrBlank()) {
            listErrors.add(ErrorCode.PHONENUMBER_IS_NULL)
        } else {
            if (value.length != 11)
                listErrors.add(ErrorCode.INVALID_PHONENUMBER_SIZE)
            if (!regexPhone.matches(value))
                listErrors.add(ErrorCode.INVALID_CHARACTER_IN_PHONENUMBER)
        }
        return listErrors
    }
}
class FIOValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regexFio = Regex("^[А-ЯЁ][а-яё]{0,15}\$")
        val listErrors = ArrayList<ErrorCode>()
        if (value.isNullOrBlank()) {
            listErrors.add(ErrorCode.NAME_OR_LASTNAME_IS_NULL)
        } else {
            if (value.length >= 16)
                listErrors.add(ErrorCode.INVALID_CHARACTER_FIO_SIZE)
            if (!regexFio.matches(value))
                listErrors.add(ErrorCode.INVALID_CHARACTER_IN_FIO)
        }
        return listErrors
    }
}
class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regexEmail = Regex("^[A-Za-z@.]{0,32}\$")
        val listErrors = ArrayList<ErrorCode>()
        if (value.isNullOrBlank()) {
            listErrors.add(ErrorCode.EMAIL_IS_NULL)
        } else {
            if (value.length >= 32)
                listErrors.add(ErrorCode.INVALID_EMAIL_SIZE)
            if (!regexEmail.matches(value))
                listErrors.add(ErrorCode.INVALID_EMAIL)
        }
        return listErrors
    }
}
class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regexSnils = Regex("\\d{2,11}\$")
        val listErrors = ArrayList<ErrorCode>()
        if (value.isNullOrBlank()) {
            listErrors.add(ErrorCode.SNILS_IS_NULL)
        } else {
            if (value.length != 11)
                listErrors.add(ErrorCode.INVALID_SNILS_SIZE)
            if (!regexSnils.matches(value))
                listErrors.add(ErrorCode.INVALID_CHARACTER_SNILS)
            if(!controlSum(value))
                listErrors.add(ErrorCode.INVALID_SNILS)
        }
        return listErrors
    }
}

//* Каждая цифра СНИЛС умножается на номер своей позиции (позиции отсчитываются с конца);
//Полученные произведения суммируются;
//Если сумма меньше 100, то контрольное число равно самой сумме;
//Если сумма равна 100 или 101, то контрольное число равно 00;

fun controlSum(value: String): Boolean {
    val snils = value.substring(0,9)
    val yy = value.substring(9).toInt()
    var sum = 0
    var position = 9
    for(i in 0..8){
        sum += snils[i].toString().toInt() * position
        position--
    }
    return sum % 101 % 100 == yy

}


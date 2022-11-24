import java.util.regex.Pattern

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errors = ArrayList<ErrorCode>()
        val CYRILLIC_CHARS = "[ЁёА-я]"
        if (!value.isValid(CYRILLIC_CHARS)){
            errors.add(ErrorCode.INVALID_CYRILLIC)
        }
        if (!value.isValid("^[\\D]{0,16}\$")){
            errors.add(ErrorCode.INVALID_COUNT)
        }

        return errors;
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errors = ArrayList<ErrorCode>()
        if (!value.isValid("^[\\d]{11}\$")){
            errors.add(ErrorCode.INVALID_PHONE_NUMBER)
        }
        if (!value.isValid("[7-8]{1}[\\d]{10}")){
            errors.add(ErrorCode.INVALID_PHONE_NUMBER)
        }
        return errors;
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val emailPattern = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
        val errors = ArrayList<ErrorCode>()
        if (!value.isValid(emailPattern)){
            errors.add(ErrorCode.INVALID_EMAIL)
        }
        if (!value.isValid("^[\\D]{0,32}\$")){
            errors.add(ErrorCode.INVALID_EMAIL)
        }
        return errors;

    }
}

class SnilsValidator : Validator<String>() {
    private val indexMass = longArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1)
    override fun validate(value: String?): List<ErrorCode> {
        val snilsPattern = ""
        val errors = ArrayList<ErrorCode>()
        if (!value.isValid(snilsPattern)){
            errors.add(ErrorCode.INVALID_SNILS)
        }
        if (!value.isValid("^[\\d]{0,11}\$")){
            errors.add(ErrorCode.INVALID_SNILS)
        }
        if (value!=null && !checkSum(value)){
            errors.add(ErrorCode.INVALID_SNILS)
        }
        return errors;
    }

    private fun checkSum(snils: String): Boolean {
        val snil: String = snils.substring(0, 9)
        val s: Int = snils.substring(9, 11).toInt()
        var sum: Long = 0
        val snich = snil.toCharArray()
        for (i in 0..8) {
            sum += indexMass.get(i) * (snich[i] - '0')
        }
        return sum % 101 % 100 == s.toLong()
    }
}

fun String?.isValid(pattern: String): Boolean{
    if (this !=null) {
        val compile = Pattern.compile(pattern)
        val matcher = compile.matcher(this)
        return matcher.find()
    }else{
        return false;
    }
}

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

/**
 * **Имя и Фамилия** - только кириллица, не более 16 символов каждое поле <br>
 */
object NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[ёЁА-я]{1,16}".toRegex()
        if (value?.matches(regex) != true) {
            return listOf(ErrorCode.INVALID_NAME)
        }
        return emptyList()
    }
}

/**
 * **Телефон** - начинается с 7 или 8ки, только цифры, 11 знаков <br>
 */
object PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "[7,8]\\d{10}".toRegex()
        if (value?.matches(regex) != true) {
            return listOf(ErrorCode.INVALID_PHONE_NUMBER)
        }
        return emptyList()
    }
}

/**
 * **Email** - латиница, с валидацией @имя_домена, не более 32 символов <br>
 */
object EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        if (value == null) {
            return listOf(ErrorCode.INVALID_EMAIL)
        }

        val regex = "\\w+(?<domen>@(\\w+\\.)*\\w+)".toRegex()

        val matchEntire = regex.matchEntire(value)

        if (((matchEntire?.groups?.get("domen")?.value?.length ?: 33) > 32)) {
            return listOf(ErrorCode.INVALID_EMAIL)
        }

        return emptyList()
    }
}

/**
 * **СНИЛС** - только цифры, 11 символов, с валидацией Контрольного числа <br>
 */
object SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val regex = "\\d{11}".toRegex()
        if (value?.matches(regex) != true || !checkKSum(value)) {
            return listOf(ErrorCode.INVALID_SNILS)
        }
        return emptyList()
    }

    /*
        Контрольное число СНИЛС рассчитывается следующим образом:
        каждая цифра СНИЛС умножается на номер своей позиции (позиции отсчитываются с конца)
        полученные произведения суммируются
        последние две цифры остатка являются контрольным числом и не должны превышать значение 100
     */
    private fun checkKSum(snils: String): Boolean {
        val str = snils.substring(0..8)
        val chek = snils.substring(9).toInt()

        var sum = 0
        for (i in 9 downTo 1) {
            sum += str[9 - i].toString().toInt() * i
        }
        val ksum = sum % 101 % 100

        return chek == ksum
    }
}
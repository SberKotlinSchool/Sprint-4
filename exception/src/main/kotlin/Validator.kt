abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

open class CommonValidator(private val reg: String, internal val error : ErrorCode) : Validator<String>() {

    override fun validate(value: String?): List<ErrorCode> {

        if (value.isNullOrEmpty() || value.isNullOrBlank())
            return listOf(ErrorCode.VALUE_IS_EMPTY)

        if (!Regex(this.reg).matches(value))
            return listOf(this.error)

        return listOf()
    }
}

// Телефон - начинается с 7 или 8ки, только цифры, 11 знаков
class PhoneValidator : CommonValidator("^[7,8]\\d{10}\$", ErrorCode.INCORRECT_PHONE_NUMBER)

// Имя и Фамилия - только кириллица, не более 16 символов каждое поле
class FirstNameValidator : CommonValidator("^[\\p{IsCyrillic}]{1,16}\$", ErrorCode.INCORRECT_FIRST_NAME)
class LastNameValidator : CommonValidator("^[\\p{IsCyrillic}]{1,16}\$", ErrorCode.INCORRECT_LAST_NAME)

// Email - латиница, с валидацией @имя_домена, не более 32 символов
class EmailValidator : CommonValidator("^(?=.{6,32}\$)[A-Za-z._]+@[A-Za-z]+\\.[a-z]{2,6}\$", ErrorCode.INCORRECT_EMAIL)

// СНИЛС - только цифры, 11 символов, с валидацией Контрольного числа
class SnilsValidator : CommonValidator("^\\d{11}\$", ErrorCode.INCORRECT_SNILS) {

    override fun validate(value: String?): List<ErrorCode> {
        return super.validate(value).ifEmpty() { validateCheckNumber(value!!) }
    }

/*
Алгоритм отсюда: http://kome-soft.blogspot.com/2017/01/c_23.html
1) Все цифры СНИЛС (т.е., 9 цифр основного номера без контрольного числа) умножаются на свою позицию в номере (позиции цифр рассчитываются справа налево)
2) Произведения разрядов номера суммируются.
3)  Если сумма меньше 100, она принимается за контрольную.
4) Если сумма - 100 или 101, то контрольной суммой становится ноль. В номере при этом записывается "00"
5) Если сумма больше 101, то она делится нацело на 101
*/
    private fun validateCheckNumber(value: String) : List<ErrorCode> {
        val main = value.substring(0, 9)
        val controlSum = value.substring(9).toInt()

        var sum = 0
        var pos = 9

        for (i in 0..8) {
            sum += main[i].toString().toInt() * pos
            pos--
        }

        return if (sum % 101 % 100 != controlSum)
            listOf(this.error)
        else
            listOf()
    }
}
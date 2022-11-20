val NAME_REGEX = Regex("[а-яА-ЯЁё]{1,16}")
val PHONE_REGEX = Regex("^(7|8)+\\d{10}")
val EMAIL_REGEX = Regex("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")
val SNILS_REGEX = Regex("\\d{11}")

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class FirstNameValidator : Validator<String>() //Имя и Фамилия - только кириллица, не более 16 символов каждое поле
{
    override fun validate(value: String?): List<ErrorCode> {
        return if (value?.matches(NAME_REGEX) == false) listOf(ErrorCode.INVALID_FIRST_NAME) else emptyList()
    }
}

class LastNameValidator : Validator<String>() //Имя и Фамилия - только кириллица, не более 16 символов каждое поле
{
    override fun validate(value: String?): List<ErrorCode> {
        return if (value?.matches(NAME_REGEX) == false) listOf(ErrorCode.INVALID_LAST_NAME) else emptyList()
    }
}

class PhoneValidator : Validator<String>() { //Телефон - начинается с 7 или 8ки, только цифры, 11 знаков

    override fun validate(value: String?): List<ErrorCode> {
        return if (value?.matches(PHONE_REGEX) == false) listOf(ErrorCode.INVALID_PHONE) else emptyList()
    }
}

class EmailValidator : Validator<String>() { //Email - латиница, с валидацией @имя_домена, не более 32 символов

    override fun validate(value: String?): List<ErrorCode> {
        return if (value?.matches(EMAIL_REGEX) == false || value?.length == 11) listOf(ErrorCode.INVALID_EMAIL) else emptyList()
    }
}

class SnilsValidator : Validator<String>() { //СНИЛС - только цифры, 11 символов, с валидацией Контрольного числа

    override fun validate(value: String?): List<ErrorCode> {
        return if (value?.matches(SNILS_REGEX) == false || !isValidControl(value)) listOf(ErrorCode.INVALID_SNILS) else emptyList()
    }

    private fun isValidControl(snils: String?): Boolean
    {
        var sum = 0
        for (i in 0..8) {
            sum += (snils?.get(i))?.digitToInt()?.times((9 - i)) ?: 0 //- каждая цифра Страхового номера умножается на номер своей позиции (позиции отсчитываются с конца)
        }

        var checkDigit = 0;

        if (sum < 100) {
            checkDigit = sum; // Если сумма меньше 100, то контрольное число равно самой сумме;

        } else if (sum > 101) {
            checkDigit = (sum % 101); //Если сумма больше 101, то сумма делится нацело на 101 и контрольное число определяется остатком от деления аналогично предыдущим двум пунктам.

            if (checkDigit == 100) {
                checkDigit = 0; // Если сумма равна 100 или 101, то контрольное число равно 00;
            }
        }
        if (checkDigit == (snils?.takeLast(2)?.toInt() ) ) {
            return true
        }

        return false;
    }
}
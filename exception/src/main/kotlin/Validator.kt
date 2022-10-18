abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

const val PHONE_REGEXP = "^[7,8]\\d{10}$"
const val NAME_REGEXP = "^[А-ЯЁ][а-яё]{0,15}$"
const val EMAIL_REGEXP = "^\\w+@\\w{1,29}\\.\\w{2,5}$"
const val SNILS_REGEXP = "^\\d{11}$"

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        value?.let {
            if (Regex(PHONE_REGEXP).matches(value)) {
                return emptyList()
            }
        }
        return listOf(ErrorCode.INVALID_PHONE)
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        value?.let {
            if (Regex(NAME_REGEXP).matches(value)) {
                return emptyList()
            }
        }
        return listOf(ErrorCode.INVALID_CHARACTER)
    }
}

class EMailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        value?.let {
            if (value.length < 33 && Regex(EMAIL_REGEXP).matches(value)) {
                return emptyList()
            }
        }
        return listOf(ErrorCode.INVALID_EMAIL)
    }
}

class SNILSValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        value?.let {
            if (Regex(SNILS_REGEXP).matches(value) && isValidSNILS(value)) {
                return emptyList()
            }
        }
        return listOf(ErrorCode.INVALID_SNILS)
    }

    private fun isValidSNILS(value: String?): Boolean {
        value?.let {
            if (value.length != 11) {
                return false
            }
            val checkSum = value.substring(9).toInt()
            var num = 0
            for (i in 0 until 9) {
                num += (value[i].code - 48) * (9 - i)
            }
            return (num % 101 == checkSum)
        }
        return false
    }
}
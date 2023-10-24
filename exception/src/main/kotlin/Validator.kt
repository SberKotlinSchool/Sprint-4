abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return value?.let {

            val errorCodeList = mutableListOf<ErrorCode>()

            if (it.length < 11 || it.length > 11) {
                errorCodeList.add(ErrorCode.INVALID_PHONE_LENGTH)
            }

            if (!it.startsWith("7") && !it.startsWith("8")) {
                errorCodeList.add(ErrorCode.INVALID_PHONE_NUMBER)
            }

            return errorCodeList
        } ?: listOf(ErrorCode.INVALID_DATA)
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return value?.let {

            val errorCodeList = mutableListOf<ErrorCode>()

            if (containsCyrillic(it)) {
                errorCodeList.add(ErrorCode.INVALID_NAME_LANGUAGE)
            }

            if (it.length > 16) {
                errorCodeList.add(ErrorCode.INVALID_NAME_LENGTH)
            }

            return errorCodeList
        } ?: listOf(ErrorCode.INVALID_DATA)

    }

    private fun containsCyrillic(text: String): Boolean {
        val cyrillicPattern = "[а-яА-Я]+".toRegex()
        return !cyrillicPattern.containsMatchIn(text)
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return value?.let {

            val errorCodeList = mutableListOf<ErrorCode>()

            if (isValidEmail(it)) {
                errorCodeList.add(ErrorCode.INVALID_EMAIL)
            }

            if (it.length > 32) {
                errorCodeList.add(ErrorCode.INVALID_EMAIL_LENGTH)
            }
            return errorCodeList
        } ?: listOf(ErrorCode.INVALID_DATA)
    }


    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@mail.ru$")
        return !emailRegex.matches(email)
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return value?.let {
            val errorCodeList = mutableListOf<ErrorCode>()

            val cleanSnils = value.replace(Regex("\\D"), "")

            if (cleanSnils.length != 11) {
                errorCodeList.add(ErrorCode.INVALID_LENGTH_SNILS)
                return errorCodeList
            }

            if (!checkControlSum(value)) {
                errorCodeList.add(ErrorCode.INVALID_CONTROL_SUM_SNILS)
            }
            return errorCodeList
        } ?: listOf(ErrorCode.INVALID_DATA)
    }

    private fun checkControlSum(value: String): Boolean {
        val digits = value.map { it.toString().toInt() }

        val checksum = digits.take(9)
            .reversed()
            .mapIndexed { index, digit -> digit * (index + 1) }
            .sum()

        val expectedChecksum = if (checksum < 100) {
            checksum
        } else if (checksum == 100 || checksum == 101) {
            0
        } else {
            checksum % 101
        }

        return expectedChecksum == digits.takeLast(2).reduce { acc, digit -> acc * 10 + digit }
    }
}
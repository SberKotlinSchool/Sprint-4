abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return value?.let {

            val errorCodeList = mutableListOf<ErrorCode>()

            if (it.length < 11 || it.length > 11) {
                errorCodeList.add(ErrorCode.INVALID_LENGTH_NUMBER)
            }

            if (!it.startsWith("7") && !it.startsWith("8")) {
                errorCodeList.add(ErrorCode.INVALID_NUMBER)
            }

            return errorCodeList
        } ?: listOf(ErrorCode.INVALID_DATA)
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return value?.let {

            val errorCodeList = mutableListOf<ErrorCode>()

            if (containsNonCyrillicCharacters(it)) {
                errorCodeList.add(ErrorCode.INVALID_CYRILLIC_CHARACTERS)
            }

            if (it.length > 16) {
                errorCodeList.add(ErrorCode.INVALID_PERSON_DATA_LENGTH)
            }

            return errorCodeList
        } ?: listOf(ErrorCode.INVALID_DATA)

    }

    private fun containsNonCyrillicCharacters(input: String): Boolean {
        val regex = Regex("[а-яА-Я]+")
        return !regex.containsMatchIn(input)
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return value?.let {

            if (!isEmailValid(value)) {
                listOf(ErrorCode.INVALID_EMAIL_FORMAT)
            }

            emptyList()
        } ?: listOf(ErrorCode.INVALID_DATA)
    }

    private fun isEmailValid(email: String): Boolean {
        val pattern = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$")

        if (pattern.matches(email))


        return pattern.matches(email) && email.length > 32 && !email.contains(Regex("[а-яА-Я]"))
    }

    private fun containsNonLatinCharacters(input: String): Boolean {
        val regex = Regex("[^A-Za-z]+")
        return regex.containsMatchIn(input)
    }

    private fun containsNonEmailDomain(input: String): Boolean {
        val regex = Regex("@[a-zA-Z0-9_]+")
        return !regex.containsMatchIn(input)
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        return value?.let {
            val errorCodeList = mutableListOf<ErrorCode>()

            val cleanSnils = value.replace(Regex("\\D"), "")

            if (cleanSnils.length > 11) {
                errorCodeList.add(ErrorCode.INVALID_LENGTH_SNILS)
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
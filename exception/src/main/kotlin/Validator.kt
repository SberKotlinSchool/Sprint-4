abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

object SnilsCheckSum {
    fun getValidSum(value: String?): Boolean {
        var sum = 0
        var checkDigit = 0
        var result = false
        for (i in 0 until 9) {
            sum += value?.get(i).toString().toInt() * (9 - i)
        }

        if (sum < 100) {
            checkDigit = sum
        }
        if (sum > 101) {
            checkDigit = sum % 101
            if (checkDigit == 100) {
                checkDigit = 0
            }
        }

        if (checkDigit == value?.takeLast(2)?.toInt()) {
            result = true
        }
        return result
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfError = mutableListOf<ErrorCode>()
        if (value != null) {
            if (value.length > 11) {
                listOfError.add(ErrorCode.INVALID_LENGTH_PHONE)
            }
            if (value
                    .toCharArray()
                    .filter { it in '0'..'9' }
                    .joinToString(separator = "") != value.lowercase()
            ) {
                listOfError.add(ErrorCode.INVALID_CHARACTER)
            }
            if (!(value.startsWith('8') || value.startsWith('7'))) {
                listOfError.add(ErrorCode.INVALID_START_CHAR_PHONE)
            }
            return listOfError.toList()
        }
        listOfError.add(ErrorCode.INVALID_OBJECT)
        return listOfError.toList()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfError = mutableListOf<ErrorCode>()
        if (value != null) {
            if (value.length > 32) {
                listOfError.add(ErrorCode.INVALID_LENGTH_EMAIL)
            }
            if (!value.contains('@') || value.endsWith('@')) {
                listOfError.add(ErrorCode.INVALID_DOMAIN)
            }
            if (value
                    .substringBefore('@')
                    .lowercase()
                    .toCharArray()
                    .filter { it in 'a'..'z' }
                    .joinToString(separator = "") != value
                                                     .substringBefore('@')
                                                     .lowercase()
            ) {
                listOfError.add(ErrorCode.INVALID_CHARACTER)
            }

            return listOfError.toList()
        }
        listOfError.add(ErrorCode.INVALID_OBJECT)
        return listOfError.toList()

    }
}

class SnilsValidator : Validator<String>() {

    override fun validate(value: String?): List<ErrorCode> {
        val listOfError = mutableListOf<ErrorCode>()
        if (value != null) {
            if (value.length > 11) {
                listOfError.add(ErrorCode.INVALID_LENGTH_SNILS)
            }
            if (value
                    .toCharArray()
                    .filter { it in '0'..'9' }
                    .joinToString(separator = "") != value.lowercase()
            ) {
                listOfError.add(ErrorCode.INVALID_CHARACTER)
            }
            if (listOfError.isEmpty()) {
                if(!SnilsCheckSum.getValidSum(value)){
                    listOfError.add(ErrorCode.SUM_SNILS_ERROR)
                }
            }
            return listOfError.toList()
        }
        listOfError.add(ErrorCode.INVALID_OBJECT)
        return listOfError.toList()
    }
}

class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfError = mutableListOf<ErrorCode>()
        if (value != null) {
            if (value.length > 16) {
                listOfError.add(ErrorCode.INVALID_LENGTH_FN)
            }
            if (value
                    .lowercase()
                    .toCharArray()
                    .filter { it in 'а'..'я' || it == 'ё' }
                    .joinToString(separator = "") != value.lowercase()
            ) {
                listOfError.add(ErrorCode.INVALID_CHARACTER)
            }
            return listOfError.toList()
        }
        listOfError.add(ErrorCode.INVALID_OBJECT)
        return listOfError.toList()
    }
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfError = mutableListOf<ErrorCode>()
        if (value != null) {
            if (value.length > 16) {
                listOfError.add(ErrorCode.INVALID_LENGTH_LN)
            }
            if (value
                    .lowercase()
                    .toCharArray()
                    .filter { it in 'а'..'я' || it == 'ё' }
                    .joinToString(separator = "") != value.lowercase()
            ) {
                listOfError.add(ErrorCode.INVALID_CHARACTER)
            }
            return listOfError.toList()
        }
        listOfError.add(ErrorCode.INVALID_OBJECT)
        return listOfError.toList()
    }
}
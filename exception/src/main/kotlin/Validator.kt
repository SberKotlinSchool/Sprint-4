import java.util.regex.Pattern


abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfErrors = ArrayList<ErrorCode>()

        if (value.isNullOrEmpty()) {
            listOfErrors.add(ErrorCode.EMPTY_PHONE)
        } else {

            if (!value.contains("^[7-8]([0-9]+)$".toRegex()))
                listOfErrors.add(ErrorCode.INVALID_PHONE_CHARACTER)

            if (value.length != 11)
                listOfErrors.add(ErrorCode.INVALID_PHONE_LENGTH)
        }
        return listOfErrors
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfErrors = ArrayList<ErrorCode>()

        if (value.isNullOrEmpty()) {
            listOfErrors.add(ErrorCode.EMPTY_NAME)
        } else {
            if (!Pattern.matches(".*\\p{InCyrillic}.*", value))
                listOfErrors.add(ErrorCode.INVALID_NAME_CHARACTER)

            if ((value.length > 16))
                listOfErrors.add(ErrorCode.INVALID_NAME_LENGTH)
        }
        return listOfErrors
    }
}

class SurnameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfErrors = ArrayList<ErrorCode>()

        if (value.isNullOrEmpty()) {
            listOfErrors.add(ErrorCode.EMPTY_SURNAME)
        } else {
            if (!Pattern.matches(".*\\p{InCyrillic}.*", value))
                listOfErrors.add(ErrorCode.INVALID_SURNAME_CHARACTER)

            if ((value.length > 16))
                listOfErrors.add(ErrorCode.INVALID_SURNAME_LENGTH)
        }
        return listOfErrors
    }
}


class SnilsValidator : Validator<String>() {

    private fun isSnilsValid(value: String?): Boolean {
        if (!value.isNullOrEmpty()) {
            if (value.substring(0, 9) <= "001001998") {
                return true
            }
            val valueLength = value.length - 2
            var sumForCheck = 0
            for (i in 0..8) {
                sumForCheck += value.substring(i, i + 1).toInt() * (valueLength - i)
            }
            if (sumForCheck > 101) {
                sumForCheck %= 101
            }
            if (sumForCheck == 100 || sumForCheck == 101) {
                sumForCheck = 0
            }
            return sumForCheck == value.substring(9, 11).toInt()
        }
        return false
    }

    override fun validate(value: String?): List<ErrorCode> {
        val listOfErrors = ArrayList<ErrorCode>()

        if (value.isNullOrEmpty()) {
            listOfErrors.add(ErrorCode.EMPTY_SNILS)
        } else {
            if (!Pattern.matches("^\\d+\$", value))
                return listOf(ErrorCode.INVALID_SNILS_CHARACTER)

            if (value.length != 11)
                return listOf(ErrorCode.INVALID_SNILS_LENGTH)

            if (!isSnilsValid(value)) {
                return listOf(ErrorCode.INVALID_SNILS_CHECKSUM)
            }
        }
        return listOfErrors
    }

}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        val listOfErrors = ArrayList<ErrorCode>()

        if (value.isNullOrEmpty()) {
            listOfErrors.add(ErrorCode.EMPTY_EMAIL)
        } else {

            if (!EMAIL_ADDRESS_PATTERN.matcher(value).matches())
                listOfErrors.add(ErrorCode.INVALID_EMAIL_CHARACTER)

            if (value.length > 32)
                listOfErrors.add(ErrorCode.INVALID_EMAIL_LENGTH)
        }
        return listOfErrors
    }
}
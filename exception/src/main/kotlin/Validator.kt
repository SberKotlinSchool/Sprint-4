abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) return listOf(ErrorCode.EMPTY)
        val errorList = mutableListOf<ErrorCode>()
        if (!value.startsWith('7') && !value.startsWith('8'))
            errorList.add(ErrorCode.INVALID_PHONE_COUNTRY)
        if (value.length != 11)
            errorList.add(ErrorCode.INVALID_LENGTH_PHONE)
        if (!value.matches("[0-9]+".toRegex())) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
            errorList.add(ErrorCode.INVALID_CHARACTER_PHONE)
        }
        return errorList
    }
}

class SNILSValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) return listOf(ErrorCode.EMPTY)
        val errorList = mutableListOf<ErrorCode>()
        if (value.length != 11)
            errorList.add(ErrorCode.INVALID_LENGTH_SNILS)
        if (!value.matches("[0-9]+".toRegex())) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
            errorList.add(ErrorCode.INVALID_CHARACTER_SNILS)
        }

        var checkSum = 0
        value.substring(0, 9).mapIndexed { index, c ->
            checkSum += (c.code - 48) * (9 - index)
        }
        if( checkSum == 100 || checkSum == 101) checkSum = 0
        if( checkSum > 101 ) checkSum %= 101

        if (checkSum != value.substring(9).toInt())
            errorList.add(ErrorCode.INVALID_SNILS_CHECKBIT)
        return errorList
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) return listOf(ErrorCode.EMPTY)
        val errorList = mutableListOf<ErrorCode>()
        if (value.length > 16)
            errorList.add(ErrorCode.INVALID_LENGTH_NAME_OR_SURNAME)
        if (!value.matches("[а-яА-Я]+".toRegex())) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
            errorList.add(ErrorCode.INVALID_CHARACTER_NAMEorSURNAME)
        }
        return errorList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        if (value == null) return listOf(ErrorCode.EMPTY)
        val errorList = mutableListOf<ErrorCode>()
        if (value.length > 32)
            errorList.add(ErrorCode.INVALID_LENGTH_EMAIL)
        if (!value.matches("([a-zA-Z]|@|\\.)+".toRegex())) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
            errorList.add(ErrorCode.INVALID_CHARACTER_EMAIL)
        }
        if (!value.matches("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+".toRegex())) {
            errorList.add(ErrorCode.INVALID_EMAIL_PATTERN)
        }
        return errorList
    }
}

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfErrors  = ArrayList<ErrorCode>()

        requireNotNull(value) { return listOf(ErrorCode.NULL_PHONE)}

        if ( !value.contains("^[7-8]([0-9]+)$".toRegex()) )
            listOfErrors.add(ErrorCode.INVALID_PHONE_CHARACTER)

        if(value.length != 11)
            listOfErrors.add(ErrorCode.INVALID_PHONE_LENGTH)

        return listOfErrors
    }
}
class FirstAndLastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfErrors  = ArrayList<ErrorCode>()

        requireNotNull(value) { return listOf(ErrorCode.NULL_NAME)}

        if (!value.contains("^\\p{InCyrillic}+$".toRegex()))
            listOfErrors.add(ErrorCode.INVALID_NAME_FORMAT)

        if ((value.length > 16) )
            listOfErrors.add(ErrorCode.INVALID_NAME_LENGTH)

        return listOfErrors
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        requireNotNull(value) { return listOf(ErrorCode.NULL_SNILS)}

        if ( !value.contains("^\\d+$".toRegex()) || value.length!=11) // Проверка строки на числа и длину снилса
            return listOf(ErrorCode.INVALID_SNILS_FORMAT)

        var i = 9
        var sum = 0
        value.substring(0,9).forEach {
            sum+=Character.getNumericValue(it)*i // перемножаем сумму
            i--
        }
        if ( sum%101 != value.substring(9,11).toInt() )
            return listOf(ErrorCode.INVALID_SNILS_CHECKSUM)

        return listOf()
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val listOfErrors  = ArrayList<ErrorCode>()

        requireNotNull(value) { return listOf(ErrorCode.NULL_EMAIL)}

        if ( !value.contains("^\\w+@\\w+[.]\\w+$".toRegex()))
            listOfErrors.add(ErrorCode.INVALID_EMAIL)

        if ( value.length > 32 )
            listOfErrors.add(ErrorCode.INVALID_EMAIL_LENGTH)

        return listOfErrors
    }
}
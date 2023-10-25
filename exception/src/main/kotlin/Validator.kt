val PHONE_LENGTH = 11
val SNILS_LENGTH = 11
val MAX_NAME_LENGTH = 16
val MAX_EMAIL_LENGTH = 32

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        val listError = ArrayList<ErrorCode>()

        if ( !( value?.matches( Regex("^[78]\\d+" ) ) ?: false ) )
            listError.add(ErrorCode.INVALID_CHARACTER)

        if ( !( value?.length == PHONE_LENGTH ?: false )  )
            listError.add(ErrorCode.INVALID_LENGTH)

        return listError
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        val listError = ArrayList<ErrorCode>()

        if ( !( value?.matches( Regex("[а-яА-Я]+" ) ) ?: false ) )
            listError.add(ErrorCode.INVALID_CHARACTER)

        if ( ( value?.length ?: MAX_NAME_LENGTH + 1 ) > MAX_NAME_LENGTH )
            listError.add(ErrorCode.INVALID_LENGTH)

        return listError
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        val listError = ArrayList<ErrorCode>()

        if ( !( value?.matches( Regex("[a-zA-Z@\\.]+" ) ) ?: false ) )
            listError.add(ErrorCode.INVALID_CHARACTER)

        if ( !( value?.matches( Regex("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+" ) ) ?: false ) )
            listError.add(ErrorCode.INVALID_FORMAT)

        if ( ( value?.length ?: MAX_EMAIL_LENGTH + 1 ) > MAX_EMAIL_LENGTH )
            listError.add(ErrorCode.INVALID_LENGTH)

        return listError
    }
}
class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {

        val listError = ArrayList<ErrorCode>()

        if ( !( value?.matches( Regex("\\d+" ) ) ?: false ) )
            listError.add(ErrorCode.INVALID_CHARACTER)

        if ( !( value?.length == SNILS_LENGTH ?: false )  )
            listError.add(ErrorCode.INVALID_LENGTH)

        if ( value != null && value.length == SNILS_LENGTH && !checkControlSum(value) ) {
            listError.add(ErrorCode.INVALID_SUM_SNILS)
        }
        return listError
    }

fun checkControlSum( value: String) : Boolean {

    val sum = when( val sum: Int = value.substring(0, 9)
                                      .mapIndexed{ ind, letter -> letter.digitToInt() * ( 9 - ind ) }
                                      .sum()  ){
               in 1 .. 99 -> sum
               100, 101 -> 0
               else -> sum % 101
            }
    return value.substring(9).toInt() == sum
}
}


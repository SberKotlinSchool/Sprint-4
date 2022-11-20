abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        var errorCodes : List<ErrorCode> = emptyList()
        errorCodes = errorCodes.plus(validateLenght(value))
        errorCodes = errorCodes.plus(validateCharacters(value))
        errorCodes = errorCodes.plus(validateNumber(value))
        return errorCodes
    }

    private fun validateLenght(value: String?): List<ErrorCode>{
        val length = value?.length ?: 0
        return if(length != 11){
            listOf(ErrorCode.INVALID_LENGTH_IN_PHONE_NUMBER)
        } else emptyList()
    }

    private fun validateCharacters(value: String?): List<ErrorCode>{
        val bufferValue = value ?: "0"
        return if(bufferValue.toCharArray().all { it in '0'..'9' }){
            emptyList()
        } else listOf(ErrorCode.INVALID_CHARACTER_IN_PHONE_NUMBER)
    }

    private fun validateNumber(value: String?): List<ErrorCode>{
        val bufferValue = value ?: "7"
        return if(bufferValue.toCharArray().first() in '7'..'8'){
            emptyList()
        } else listOf(ErrorCode.INVALID_PHONE_NUMBER)
    }
}

class NameValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        var errorCodes : List<ErrorCode> = emptyList()
        errorCodes = errorCodes.plus(validateLength(value))
        errorCodes = errorCodes.plus(validateCharacters(value))
        return errorCodes
    }

    private fun validateLength(value: String?): List<ErrorCode>{
        val length = value?.length ?: 999
        return if(length > 16){
            listOf(ErrorCode.INVALID_LENGTH_IN_NAME)
        } else emptyList()
    }

    private fun validateCharacters(value: String?): List<ErrorCode>{
        val bufferValue = value ?: "ж"
        return if (bufferValue.toCharArray().all { it in 'А'..'я' }){
            emptyList()
        } else listOf(ErrorCode.INVALID_CHARACTER_IN_NAME)

    }

}

class EmailValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        var errorCodes : List<ErrorCode> = emptyList()
        errorCodes = errorCodes.plus(validateLength(value))
        errorCodes = errorCodes.plus(validateCharacters(value))
        errorCodes = errorCodes.plus(validateDomain(value))
        return errorCodes
    }

    private fun validateLength(value: String?): List<ErrorCode>{
        val length = value?.length ?: 999
        return if(length > 32) {
            listOf(ErrorCode.INVALID_LENGTH_IN_EMAIL)
        } else emptyList()
    }

    private fun validateCharacters(value: String?): List<ErrorCode>{
        val bufferValue = value ?: ""
        val nonLatinChar = bufferValue.partition {it in 'A'..'z'}.second
        return if(nonLatinChar.all { it == '@' || it == '.' }) {
            emptyList()
        } else listOf(ErrorCode.INVALID_CHARACTER_IN_EMAIL)
    }

    private fun validateDomain(value: String?): List<ErrorCode>{
        val bufferValue = value ?: ""
        val nonLatinChar = bufferValue.partition {it in 'A'..'z'}.second
        if (nonLatinChar != "@.") return listOf(ErrorCode.INVALID_DOMAIN_IN_EMAIL)

        val domain = bufferValue.split('.').last()
        if(domain != "ru" && domain != "com") return listOf(ErrorCode.INVALID_DOMAIN_IN_EMAIL)
        return emptyList()
    }
}

class SNILSValidator : Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        var errorCodes : List<ErrorCode> = emptyList()
        errorCodes = errorCodes.plus(validateLength(value))
        errorCodes = errorCodes.plus(validateCharacters(value))
        errorCodes = errorCodes.plus(validateCheckSum(value))
        return errorCodes
    }

    private fun validateLength(value: String?): List<ErrorCode>{
        val length = value?.length ?: 0
        return if(length!= 11){
            listOf(ErrorCode.INVALID_LENGTH_IN_SNILS)
        } else emptyList()
    }

    private fun validateCharacters(value: String?): List<ErrorCode>{
        val bufferValue = value ?: ""
        return if (bufferValue.toCharArray().all{it in '0'..'9'}){
            emptyList()
        } else listOf(ErrorCode.INVALID_CHARACTER_IN_SNILS)
    }

    private fun validateCheckSum(value: String?): List<ErrorCode>{
        val length = value?.length ?: 0
        if (length != 11) return emptyList()
        var sum = 0
        for(i in 0..8){
            sum += (9 - i) * (value!!.get(i) - '0')
        }
        val checkSum = (value!!.get(9) - '0') * 10 + (value!!.get(10) - '0')
        return if (sum % 101 % 100 == checkSum){
            emptyList()
        } else listOf(ErrorCode.INVALID_SNILS)
    }

}
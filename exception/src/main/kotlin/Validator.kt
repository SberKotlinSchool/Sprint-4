

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
    abstract val checkLengthError: (String) -> Boolean
    abstract val checkSymbolsError: (String) -> Boolean
}

class PhoneValidator : Validator<String>() {
    private val phoneRegex = Regex("^[7-8]{1}[0-9]{10}")
    private val incorrectSymbolRegex = Regex("^[^7-8]|[^0-9]")

    override val checkLengthError: (String?) -> Boolean
        get() = { it?.length != 11 }
    override val checkSymbolsError: (String?) -> Boolean
        get() = { it?.contains(incorrectSymbolRegex) == true }
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = mutableListOf<ErrorCode>()

        if (value?.matches(phoneRegex) != true) {
            if (checkLengthError(value)) {
                errorList.add(ErrorCode.INVALID_PHONE_LENGTH)
            }
            if (checkSymbolsError(value)) {
                errorList.add(ErrorCode.INVALID_PHONE_CHARACTER)
            }
        }

        return errorList
    }


}

class ClientNameValidator() : Validator<String>() {
    private val validatorRegex = Regex("[А-я]{1,16}")
    private val incorrectSymbolRegex = Regex("[^А-я]")

    override val checkLengthError: (String) -> Boolean
        get() = { it.length > 16 }

    override val checkSymbolsError: (String) -> Boolean
        get() = { it.contains(incorrectSymbolRegex) }
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = mutableListOf<ErrorCode>()

        if (value != null) {
            if (!value.matches(validatorRegex)) {
                if (checkLengthError(value)) {
                    errorList.add(ErrorCode.INVALID_FIO_LENGTH)
                }
                if (checkSymbolsError(value)) {
                    errorList.add(ErrorCode.INVALID_CLIENT_FIO)
                }
            }
        }


        return errorList
    }
}

class EmailValidator(): Validator<String>() {
    private val emailRegex = Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$")
    override val checkLengthError: (String) -> Boolean
        get() = { it.length > 32 }

    override val checkSymbolsError: (String) -> Boolean
        get() = { it.contains(emailRegex) }


    override fun validate(value: String?): List<ErrorCode> {
        val errorList = mutableListOf<ErrorCode>()

        if (value != null) {
            if (checkLengthError(value))
                errorList.add(ErrorCode.INVALID_EMAIL_LENGTH)

            if (!checkSymbolsError(value)) {
                errorList.add(ErrorCode.INVALID_EMAIL)
            }
        }
        return errorList
    }
}


class SnilsValidator(): Validator<String>() {
    private val snilsCorrectRegex = Regex("[0-9]{11}")
    private val incorrectSymbolsRegex = Regex("[^0-9]")
    override val checkLengthError: (String) -> Boolean
        get() = { it.length > 11 }

    override val checkSymbolsError: (String) -> Boolean
        get() = { it.contains(incorrectSymbolsRegex) }

    override fun validate(value: String?): List<ErrorCode> {
        val errorList = mutableListOf<ErrorCode>()

        if (value != null) {

            if (value.matches(snilsCorrectRegex)) {
                if (!checkControlSum(value)) {
                    errorList.add(ErrorCode.INVALID_CONTROL_SUM_SNILS)
                }
            } else {
                if (checkLengthError(value)) {
                    errorList.add(ErrorCode.INVALID_SNILS_LENGTH)
                }

                if (checkSymbolsError(value)) {
                    errorList.add(ErrorCode.INVALID_SNILS_CHARACTER)
                }
            }
        }

        return errorList
    }


    private fun checkControlSum(value: String): Boolean {
        val snilsValue = value.substring(0..8)
        val controlSum = value.substring(9).toInt()
        var actualSum = 0

        var positionCounter = 9

        snilsValue.forEach {
            actualSum += it.toString().toInt() * positionCounter
            positionCounter--
        }

//        println("Номер снилс - $snilsValue")
//        println("Контрольная сумма в конце строки - $controlSum")
//        println("Актуальная контрольная сумма - $actualSum")

        return actualSum == controlSum
    }
}
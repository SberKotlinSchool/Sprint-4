abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.PHONE_IS_NULL)
                return@apply
            }
            checkCondition({ value.startsWith('7') || value.startsWith('8') },
                { add(ErrorCode.INVALID_FIRST_CHARACTER_OF_PHONE) })
            checkCondition({ value.length == 11 }, { add(ErrorCode.INVALID_PHONE_SIZE) })
            checkCondition({ "^\\d+\$".toRegex().matches(value) }, { add(ErrorCode.INVALID_CHARACTER) })
        }
}

//только кириллица, не более 16 символов каждое поле
class FirstNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.NAME_IS_NULL)
                return@apply
            }
            checkCondition({ value.length <= 16 }, { add(ErrorCode.INVALID_FIRST_NAME_SIZE) })
            checkCondition(
                { "^[ЁёА-я@]+\$".toRegex().matches(value) },
                { add(ErrorCode.INVALID_CHARACTER_IN_FIRST_NAME) })
        }
}

class LastNameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.LAST_NAME_IS_NULL)
                return@apply
            }
            checkCondition({ value.length <= 16 }, { add(ErrorCode.INVALID_LAST_NAME_SIZE) })
            checkCondition(
                { "^[ЁёА-я]+\$".toRegex().matches(value) },
                { add(ErrorCode.INVALID_CHARACTER_IN_LAST_NAME) })
        }
}

//Email - латиница, с валидацией @имя_домена, не более 32 символов
class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.EMAIL_IS_NULL)
                return@apply
            }
            checkCondition({ value.length <= 32 }, { add(ErrorCode.INVALID_EMAIL_SIZE) })
            checkCondition(
                { "^[A-Za-z.@0-9]+\$".toRegex().matches(value) },
                { add(ErrorCode.INVALID_CHARACTER_IN_EMAIL) })
            checkCondition(
                { "^.+@.+(\\.[^\\.]+)+\$".toRegex().matches(value) },
                { add(ErrorCode.INVALID_EMAIL_FORMAT) })
        }
}

//СНИЛС - только цифры, 11 символов, с валидацией Контрольного числа
class SNILSValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.SNILS_IS_NULL)
                return@apply
            }
            checkCondition({ value.length == 11 }, { add(ErrorCode.INVALID_SNILS_SIZE) })
            checkCondition({ "^[0-9]+\$".toRegex().matches(value) }, { add(ErrorCode.INVALID_CHARACTER_IN_SNILS) })
            if (value.length == 11) {
                checkCondition(
                    { checkSum(value.substring(0..8), value.substring(9).toLong()) },
                    { add(ErrorCode.INVALID_SNILS) })
            }
        }
}

private fun checkSum(snils: String, s: Long): Boolean {
    var sum: Long = 0
    var index = 9;
    for (num in snils) {
        sum += index * num.toString().toInt()
        index--
    }
    return sum % 101 % 100 == s
}

//Избыточно, но пытаемся хоть где-то использовать try-catch
fun checkCondition(condition: () -> Boolean, errorHandler: () -> Unit) =
    try {
        require(condition.invoke())
    } catch (ex: IllegalArgumentException) {
        errorHandler()
    }

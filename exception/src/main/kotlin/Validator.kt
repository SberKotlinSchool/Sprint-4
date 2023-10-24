abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        value?.let { phoneNumber ->
            listOfNotNull(
                ErrorCode.INVALID_PHONE_START_DIGIT.takeIf { phoneNumber.first() !in setOf('7', '8') },
                ErrorCode.INVALID_PHONE_CHARACTER.takeIf { phoneNumber.any { !it.isDigit() } },
                ErrorCode.INVALID_PHONE_LENGTH.takeIf { phoneNumber.length != 11 }
            )
        } ?: listOf(ErrorCode.PHONE_IS_MISSING)
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        value?.let { name ->
            listOfNotNull(
                ErrorCode.INVALID_NAME_LANGUAGE.takeIf {
                    name.any { !it.isCyrillic() }
                },
                ErrorCode.INVALID_NAME_LENGTH.takeIf { name.isEmpty() || name.length > 16 }
            )
        } ?: listOf(ErrorCode.NAME_IS_MISSING)
}

class MailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        value?.let { mail ->
            listOfNotNull(
                ErrorCode.MAIL_DOMAIN_IS_MISSING.takeUnless { ".+@.+\\..+".toRegex() matches mail },
                ErrorCode.INVALID_MAIL_LANGUAGE.takeIf {
                    mail.any { it.isLetter() && it.isCyrillic() }
                },
                ErrorCode.INVALID_MAIL_LENGTH.takeIf { mail.isEmpty() || mail.length > 32 }
            )
        } ?: listOf(ErrorCode.MAIL_ADDRESS_IS_MISSING)
}

class SNILSValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        value
            ?.replace("[- ]".toRegex(), "")
            ?.let { snils ->
                sequence {
                    if (snils.length != 11) {
                        yield(ErrorCode.INVALID_SNILS_LENGTH)
                        return@sequence
                    }

                    if (snils.any { !it.isDigit() }) {
                        yield(ErrorCode.INVALID_SNILS_CHARACTER)
                        return@sequence
                    }

                    if (!isValidControlSum(snils)) {
                        yield(ErrorCode.INVALID_SNILS_CONTROL_NUMBER)
                    }
                }
            }?.toList() ?: listOf(ErrorCode.SNILS_IS_MISSING)

    private fun isValidControlSum(snils: String): Boolean = snils.dropLast(2).withIndex().sumOf {
        it.value.digitToInt() * (9 - it.index)
    }.let { sum ->
        val controlNumber = snils.takeLast(2).toInt()
        when(val transformedSum = sum % 101) {
            in 1..99 -> transformedSum == controlNumber
            in 100..101 -> controlNumber == 0
            else -> false
        }
    }
}

fun Char.isCyrillic() = Character.UnicodeBlock.of(this) == Character.UnicodeBlock.CYRILLIC
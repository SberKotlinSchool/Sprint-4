abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class NameValidator : Validator<String>() {

    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.EMPTY)
            } else {
                if (value.length > MAX_NAME_LENGTH) {
                    add(ErrorCode.NAME_EXCEEDS_LENGTH)
                }
                if (value.any { it !in REQUIRED_LOWERCASE_SYMBOLS && it !in REQUIRED_UPPERCASE_SYMBOLS }) {
                    add(ErrorCode.INVALID_CHARACTER)
                }
            }
        }

    companion object {

        private const val MAX_NAME_LENGTH = 16
        private val REQUIRED_LOWERCASE_SYMBOLS = 'а'..'я'
        private val REQUIRED_UPPERCASE_SYMBOLS = 'А'..'Я'
    }
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.EMPTY)
            } else {
                if (value.length != REQUIRED_PHONE_LENGTH) {
                    add(ErrorCode.PHONE_INVALID_LENGTH)
                }
                if (value.first() !in REQUIRED_START_SYMBOLS) {
                    add(ErrorCode.PHONE_STARTS_WITH_INVALID_CHARACTER)
                }
                if (value.any { it !in REQUIRED_SYMBOLS }) {
                    add(ErrorCode.INVALID_CHARACTER)
                }
            }
        }

    companion object {

        private const val REQUIRED_PHONE_LENGTH = 11
        private val REQUIRED_SYMBOLS = '0'..'9'
        private val REQUIRED_START_SYMBOLS = charArrayOf('7', '8')
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.EMPTY)
            } else {
                if (value.length > MAX_EMAIL_LENGTH) {
                    add(ErrorCode.EMAIL_EXCEEDS_LENGTH)
                }
                val emailParts = value.split('@')
                if (emailParts.size == REQUIRED_EMAIL_PARTS_NUM) {
                    val (emailName, emailDomain) = emailParts

                    if (emailName.any { it !in REQUIRED_LOWERCASE_SYMBOLS && it !in REQUIRED_UPPERCASE_SYMBOLS }) {
                        add(ErrorCode.EMAIL_INVALID_NAME)
                    }
                    if (!verifyDomain(emailDomain)) {
                        add(ErrorCode.EMAIL_INVALID_DOMAIN)
                    }
                } else {
                    add(ErrorCode.EMAIL_INVALID_DOMAIN)
                }
            }
        }

    private fun verifyDomain(domainString: String): Boolean =
        """([A-Za-z]+\.)+[A-Za-z]{2,4}""".toRegex().matches(domainString)


    companion object {

        private const val MAX_EMAIL_LENGTH = 32
        private const val REQUIRED_EMAIL_PARTS_NUM = 2
        private val REQUIRED_LOWERCASE_SYMBOLS = 'a'..'z'
        private val REQUIRED_UPPERCASE_SYMBOLS = 'A'..'Z'
    }
}

class SNILSValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> =
        mutableListOf<ErrorCode>().apply {
            if (value.isNullOrBlank()) {
                add(ErrorCode.EMPTY)
            } else {
                if (value.length != REQUIRED_SNILS_LENGTH) {
                    add(ErrorCode.SNILS_INVALID_LENGTH)
                }
                if (value.any { it !in REQUIRED_SYMBOLS }) {
                    add(ErrorCode.INVALID_CHARACTER)
                }
                if (!verifyCheckSum(value)) {
                    add(ErrorCode.SNILS_INVALID_CHECK_SUM)
                }
            }
        }

    private fun verifyCheckSum(snils: String): Boolean =
        snils.substring(0, snils.lastIndex - 1)
            .map { it.digitToInt() }
            .asReversed()
            .foldIndexed(0) { index, acc, value -> acc + value * (index + 1) }
            .let { it % 101 }
            .let { if (it == 100) 0 else it }
            .let { it == snils.substring(snils.lastIndex - 1).toInt() }


    companion object {

        private const val REQUIRED_SNILS_LENGTH = 11
        private val REQUIRED_SYMBOLS = '0'..'9'
    }
}

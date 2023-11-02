typealias Validator<T> = (T) -> List<ErrorCode>
typealias ValidatorClient = Validator<Client>

val firstNameValidator: ValidatorClient = { c ->
    c.firstName?.let {firstName ->
        listOfNotNull(
            ErrorCode.INVALID_FIRST_NAME.takeUnless { Regex("[А-ЯЁ][-А-яЁё]{1,16}$").matches(firstName) }
        )
    } ?: listOf(ErrorCode.FIRST_NAME_IS_MISSING)
}

val lastNameValidator: ValidatorClient = { c ->
    c.lastName?.let {lastName ->
        listOfNotNull(
            ErrorCode.INVALID_LAST_NAME.takeUnless { Regex("[А-ЯЁ][-А-яЁё]{1,16}$").matches(lastName) }
        )
    } ?: listOf(ErrorCode.LAST_NAME_IS_MISSING)
}


val phoneValidator: ValidatorClient = { c ->
    c.phone?.let { phone ->
        listOfNotNull(
            ErrorCode.INVALID_PHONE.takeUnless { Regex("^[78][0-9]{10}$").matches(phone) }
        )
    } ?: listOf(ErrorCode.PHONE_IS_MISSING)

}

val mailValidator: ValidatorClient = { c ->
    c.email?.let { mail ->
        listOfNotNull(
            ErrorCode.INVALID_MAIL.takeUnless {
                Regex("^(?=.{6,32}$)([A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6})$").matches(
                    mail
                )
            }
        )
    } ?: listOf(ErrorCode.MAIL_IS_MISSING)

}

val snilsValidator: ValidatorClient = { c ->
    c.snils?.replace("[- ]".toRegex(), "")?.let { snils ->
        sequenceOf(
            { ErrorCode.INVALID_SNILS.takeUnless { Regex("^[0-9]{11}$").matches(snils) } },
            { ErrorCode.INVALID_SNILS_SUM.takeUnless { isValidControlSum(snils) } },
        ).map { it() }.filterNotNull().take(1).toList()
    } ?: listOf(ErrorCode.SNILS_IS_MISSING)
}

fun isValidControlSum(snils: String): Boolean = snils.dropLast(2).withIndex().sumOf {
    it.value.digitToInt() * (9 - it.index)
}.let { sum ->
    val controlSum = snils.takeLast(2).toInt()
    when (val transformedSum = sum % 101) {
        in 1..99 -> transformedSum == controlSum
        in 100..101 -> controlSum == 0
        else -> false
    }
}
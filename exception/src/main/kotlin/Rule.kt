interface Rule {
    fun check(client: Client)
}

class FirstNameRule : Rule {

    private val pattern = "^[ЁёА-я]{1,16}\$".toRegex()
    override fun check(client: Client) {
        if (!pattern.matches(client.firstName.orEmpty())) {
            throw InvalidFirstName()
        }
    }
}

class LastNameRule : Rule {

    private val pattern = "^[ЁёА-я]{1,16}\$".toRegex()
    override fun check(client: Client) {
        if (!pattern.matches(client.lastName.orEmpty())) {
            throw InvalidLastName()
        }
    }
}

class PhoneNumberRule : Rule {

    private val pattern = "^[78][0-9]{10}\$".toRegex()
    override fun check(client: Client) {
        if (!pattern.matches(client.phone.orEmpty())) {
            throw InvalidPhoneNumber()
        }
    }
}

class EmailRule : Rule {

    private val pattern = "^(?i)[a-z]*@[a-z]*\\.[a-z]{2,6}\$".toRegex()
    override fun check(client: Client) {
        val email = client.email.orEmpty()
        if (!pattern.matches(email) || email.length > 32) {
            throw InvalidEmail()
        }
    }
}

class InsuranceRule : Rule {

    private val pattern = "^[0-9]{11}\$".toRegex()
    override fun check(client: Client) {
        val snils = client.snils.orEmpty()
        if (!pattern.matches(snils) || !validChecksum(snils)) {
            throw InvalidInsuranceNumber()
        }
    }

    private fun validChecksum(value: String): Boolean {
        var sum = 0
        for ((i, ch) in value.take(9).iterator().withIndex()) {
            sum += Character.getNumericValue(ch) * (9 - i)
        }
        return sum % 101 % 100 == value.takeLast(2).toInt()
    }
}
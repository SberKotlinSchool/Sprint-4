class Validator(
    private val rules: List<Rule> = listOf(
        FirstNameRule(),
        LastNameRule(),
        PhoneNumberRule(),
        EmailRule(),
        InsuranceRule()
    )
) {

    fun validate(client: Client) {
        for (rule in rules) {
            rule.check(client)
        }
    }
}
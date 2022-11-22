import mu.KLogging

fun List<ErrorCode>.withName(name: String): List<String> = this.map {
    "$name: ${it.msg}"
}

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorList = ArrayList<String>()
        errorList.addAll(
            FirstNameValidator()
                .validate(client.firstName)
                .withName("FirstName")
        )
        errorList.addAll(
            FirstNameValidator()
                .validate(client.lastName)
                .withName("lastName")
        )
        errorList.addAll(
            PhoneValidator()
                .validate(client.phone)
                .withName("phone")
        )
        errorList.addAll(
            EmailValidator()
                .validate(client.email)
                .withName("email")
        )
        errorList.addAll(
            SnilsValidator()
                .validate(client.snils)
                .withName("snils")
        )
        if (errorList.isNotEmpty()) {
            throw ValidationException(errorList.toTypedArray())
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}

import mu.KLogging

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorList = ArrayList<ErrorCode>()
        val validatorList = listOf(
            PhoneValidator().validate(client.phone),
            FirstNameValidator().validate(client.firstName),
            LastNameValidator().validate(client.lastName),
            EmailValidator().validate(client.email),
            SnilsValidator().validate(client.snils)
        )
        errorList.addAll(validatorList.flatten())
        if (errorList.isNotEmpty()) {
            throw ValidationException(*errorList.toTypedArray())
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}
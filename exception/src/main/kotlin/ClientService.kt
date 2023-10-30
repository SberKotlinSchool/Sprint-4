import mu.KLogging

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorList = ArrayList<ErrorCode>()
        errorList.addAll(NameValidator().validate(client.firstName))
        errorList.addAll(NameValidator().validate(client.lastName))
        errorList.addAll(PhoneValidator().validate(client.phone))
        errorList.addAll(EmailValidator().validate(client.email))
        errorList.addAll(SnilsValidator().validate(client.snils))
        if (errorList.isNotEmpty()) {
            throw ValidationException(*errorList.toTypedArray())
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}
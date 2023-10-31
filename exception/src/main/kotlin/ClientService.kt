import mu.KLogging

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client): ArrayList<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        errorList.addAll(listOf(client.firstName, client.lastName).map { LastNameFirstNameValidator().validate(it) }.flatten())
        errorList.addAll(PhoneValidator().validate(client.phone))
        errorList.addAll(EmailValidator().validate(client.email))
        errorList.addAll(SnilsValidator().validate(client.snils))
        if (errorList.isNotEmpty()) {
            throw ValidationException(*errorList.toTypedArray())
        }
        return errorList
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}
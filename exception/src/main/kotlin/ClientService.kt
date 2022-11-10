import mu.KLogging

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorList = ArrayList<ErrorCode>()

        with(errorList) {
            addAll(NameValidator().validate(client.firstName))
            addAll(NameValidator().validate(client.lastName))
            addAll(PhoneValidator().validate(client.phone))
            addAll(EmailValidator().validate(client.email))
            addAll(SNILSValidator().validate(client.snils))

            if (isNotEmpty()) {
                throw ValidationException(*toTypedArray())
            }
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}

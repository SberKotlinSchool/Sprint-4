import mu.KLogging

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        var typeAndErrors = PhoneValidator().validate(client.phone)
        typeAndErrors = NameValidator().validate(client.firstName, typeAndErrors)
        typeAndErrors = SurnameValidator().validate(client.lastName, typeAndErrors)
        typeAndErrors = EmailValidator().validate(client.email, typeAndErrors)
        typeAndErrors = SnilsValidator().validate(client.snils, typeAndErrors)

        if (typeAndErrors.isNotEmpty()) {
            throw ValidationException(typeAndErrors)
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}
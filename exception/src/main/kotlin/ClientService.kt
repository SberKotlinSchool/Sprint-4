import mu.KLogging

class ClientService {
    private val nameValidator = NameValidator()
    private val phoneValidator = PhoneValidator()
    private val emailValidator = EmailValidator()
    private val snilsValidator = SnilsValidator()

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorMap = HashMap<String, List<ErrorCode>>().also {
            it.putIfValueIsNotEmpty("firstName", nameValidator.validate(client.firstName))
            it.putIfValueIsNotEmpty("lastName", nameValidator.validate(client.lastName))
            it.putIfValueIsNotEmpty("phone", phoneValidator.validate(client.phone))
            it.putIfValueIsNotEmpty("email", emailValidator.validate(client.email))
            it.putIfValueIsNotEmpty("snils", snilsValidator.validate(client.snils))
        }
        if (errorMap.isNotEmpty()) {
            throw ValidationException(errorMap)
        }
    }

    private fun <K, V : Collection<*>> MutableMap<K, V>.putIfValueIsNotEmpty(key: K, value: V) {
        if (value.isNotEmpty()) {
            this[key] = value
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}
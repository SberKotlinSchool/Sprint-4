import mu.KLogging

class ClientService {

    private val phoneValidator = PhoneValidator()
    private val nameValidator = NameValidator()
    private val emailValidator = EmailValidator()
    private val snilsValidator = SnilsValidator()

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorMap = HashMap<String, List<ErrorCode>>()
        validatePropertyAddToMap(phoneValidator, client.phone, "phone", errorMap)
        validatePropertyAddToMap(nameValidator, client.firstName, "firstName", errorMap)
        validatePropertyAddToMap(nameValidator, client.lastName, "lastName", errorMap)
        validatePropertyAddToMap(emailValidator, client.email, "email", errorMap)
        validatePropertyAddToMap(snilsValidator, client.snils, "snils", errorMap)
        if (errorMap.isNotEmpty()) {
            throw ValidationException(errorMap)
        }
    }

    private fun <T> validatePropertyAddToMap(validator: Validator<T>,
                                             property: T?,
                                             propertyName: String,
                                             errorMap: MutableMap<String, List<ErrorCode>>) {
        val errors = validator.validate(property)
        if (errors.isNotEmpty()) {
            errorMap[propertyName] = errors
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}

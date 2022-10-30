import mu.KLogging
import validator.EmailValidator
import validator.NameValidator
import validator.PhoneValidator

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorList = ArrayList<ErrorCode>()
        errorList.addAll(PhoneValidator(client.phone).validateAll())
        errorList.addAll(NameValidator(client.firstName).validateAll())
        errorList.addAll(NameValidator(client.lastName).validateAll())
        errorList.addAll(EmailValidator(client.email).validateAll())
        // ...
        if (errorList.isNotEmpty()) {
            throw ValidationException(*errorList.toTypedArray())
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}

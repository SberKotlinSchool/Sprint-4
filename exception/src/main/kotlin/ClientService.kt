import mu.KLogging

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val validatorList = listOf(
            phoneValidator,
            firstNameValidator,
            lastNameValidator,
            mailValidator,
            snilsValidator
        )

        val errList = validatorList
            .map { it(client) }
            .filter{ it.isNotEmpty() }
            .flatten()

        if (errList.isNotEmpty()) {
            throw ValidationException(errList.toTypedArray())
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}

fun main() {
    try {
        ClientService().saveClient(Client("Дима", "Дима", "29300305685", "123@mail.ru", "1231ы312321", 1))
    } catch (e: ValidationException) {
        println(e)
    }
}
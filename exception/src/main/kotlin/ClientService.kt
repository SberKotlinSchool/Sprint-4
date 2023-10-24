import mu.KLogging

class ClientService {

    fun saveClient(client: Client): Client = client
            .also { validateClient(client) }
            .let { saveToMyPhantomDB(client) }
            .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorList = mutableListOf<Pair<String, List<ErrorCode>>>()
        errorList.add("phone" to client.phone.validatePhone())
        errorList.add("firstName" to client.firstName.validateName())
        errorList.add("lastName" to client.lastName.validateName())
        errorList.add("email" to client.email.validateMail())
        errorList.add("snils" to client.snils.validateSnils())
        errorList.filter { it.second.isNotEmpty() }.let { list ->
            if (list.isNotEmpty())
                throw ValidationException(list)
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
            .also { it.incrementVersion() }

    companion object : KLogging()
}
import mu.KLogging

class ClientService {

    private val validator = Validator()

    fun saveClient(client: Client): Client = client
        .also(validator::validate)
        .let(::saveToMyPhantomDB)
        .also { logger.info { "Успешно сохранена новая версия $it" } }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}
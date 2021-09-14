import mu.KLogging

class ClientService {

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorList = ArrayList<ErrorCode>()
        logger.info { "Валидация телефона клиента: ${client.phone}" }
        errorList.addAll(PhoneValidator().validate(client.phone).also{ if(it.isEmpty()) logger.info { "Успех" } else logger.info { it } })
        logger.info { "Валидация имени клиента: ${client.phone}" }
        errorList.addAll(FirstAndLastNameValidator().validate(client.firstName).also{ if(it.isEmpty()) logger.info { "Успех" } else logger.info { it } })
        logger.info { "Валидация фамилии клиента: ${client.lastName}" }
        errorList.addAll(FirstAndLastNameValidator().validate(client.lastName).also{ if(it.isEmpty()) logger.info { "Успех" } else logger.info { it } })
        logger.info { "Валидация почты клиента: ${client.email}" }
        errorList.addAll(EmailValidator().validate(client.email).also{ if(it.isEmpty()) logger.info { "Успех" } else logger.info { it } })
        logger.info { "Валидация снилс клиента: ${client.snils}" }
        errorList.addAll(SnilsValidator().validate(client.snils).also{ if(it.isEmpty()) logger.info { "Успех" } else logger.info { it } })

        if (errorList.isNotEmpty()) {
            throw ValidationException(*errorList.toTypedArray())
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}
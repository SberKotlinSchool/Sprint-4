import mu.KLogging

class ClientService {
    private val emptyStringValidator = EmptyStringValidator()
    private val maxLengthStringValidatorUtil = MaxLengthStringValidatorUtil()
    private val fixLengthStringValidatorUtil = FixLengthStringValidatorUtil()
    private val onlyDigitStringValidatorUtil = OnlyDigitStringValidatorUtil()
    private val properNameValidator = ProperNameValidator(emptyStringValidator, maxLengthStringValidatorUtil)
    private val phoneValidator = PhoneValidator(emptyStringValidator, fixLengthStringValidatorUtil, onlyDigitStringValidatorUtil)
    private val emailValidator = EmailValidator(emptyStringValidator, maxLengthStringValidatorUtil)
    private val snilsValidator = SnilsValidator(emptyStringValidator, onlyDigitStringValidatorUtil, fixLengthStringValidatorUtil)

    fun saveClient(client: Client): Client = client
        .also { validateClient(client) }
        .let { saveToMyPhantomDB(client) }
        .also { logger.info { "Успешно сохранена новая версия $it" } }


    private fun validateClient(client: Client) {
        val errorList = ArrayList<ErrorCode>()
        errorList.addAll(properNameValidator.validate(client.firstName))
        errorList.addAll(properNameValidator.validate(client.lastName))
        errorList.addAll(phoneValidator.validate(client.phone))
        errorList.addAll(emailValidator.validate(client.email))
        errorList.addAll(snilsValidator.validate(client.snils))
        if (errorList.isNotEmpty()) {
            throw ValidationException(errorList.toTypedArray())
        }
    }

    private fun saveToMyPhantomDB(client: Client) = client
        .also { it.incrementVersion() }

    companion object : KLogging()
}
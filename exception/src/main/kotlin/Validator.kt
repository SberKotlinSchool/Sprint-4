abstract class Validator<D>(private val data: D?) {
    private val validations = ArrayList<Validation<D>>()

    init {
        prepare()
    }

    protected abstract fun prepare()

    protected fun addValidation(validation: Validation<D>) {
        validations.add(validation)
    }

    fun validateAll(): List<ErrorCode> {
        if (data == null) {
            return listOf(ErrorCode.NULL_FIELD)
        }

        val validationResult = ArrayList<ErrorCode>()
        validations.forEach { validation ->
            validation.validate(data)
                ?.let { error -> validationResult.add(error) }
        }

        return validationResult
    }
}

class PhoneValidator(data: String?) : Validator<String>(data) {
    override fun prepare() {
        addValidation(NotEmptyStringValidation())
        addValidation(ExactLengthStringValidation(11))
        addValidation(MatchRegexStringValidation("[7|8]\\d+"))
    }
}

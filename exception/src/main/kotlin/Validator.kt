abstract class Validator<D>(private val data: D?) {
    private val validations = ArrayList<Validation<D>>()

    init {
        prepare()
    }

    protected abstract fun prepare()

    protected fun addValidation(validation: Validation<D>) = validations.add(validation)

    fun validateAll(): List<ErrorCode> {
        if (data == null) {
            return listOf(ErrorCode.NULL_FIELD)
        }

        val validationResult = ArrayList<ErrorCode>()
        validations.forEach { it.validate(data)
            ?.let { validationResult.add(it) }
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

class NameValidator(data: String?) : Validator<String>(data) {
    override fun prepare() {
        addValidation(NotEmptyStringValidation())
        addValidation(MaxLengthStringValidation(16))
        addValidation(MatchRegexStringValidation("[А-Яа-я\\s-]+"))
    }
}

class EmailValidator(data: String?) : Validator<String>(data) {
    override fun prepare() {
        addValidation(NotEmptyStringValidation())
        addValidation(MaxLengthStringValidation(32))
        addValidation(MatchRegexStringValidation("[A-z]+@[A-z]+\\.[A-z]+"))
    }
}

class SnilsValidator(data: String?) : Validator<String>(data) {
    override fun prepare() {
        addValidation(NotEmptyStringValidation())
        addValidation(ExactLengthStringValidation(11))
        addValidation(MatchRegexStringValidation("\\d+"))
        addValidation(SnilsControlNumberStringValidation())
    }
}
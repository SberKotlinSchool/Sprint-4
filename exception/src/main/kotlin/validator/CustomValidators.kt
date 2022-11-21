package validator

import validation.ExactLengthStringValidation
import validation.MatchRegexStringValidation
import validation.MaxLengthStringValidation
import validation.NotEmptyStringValidation
import validation.SnilsControlNumberStringValidation

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

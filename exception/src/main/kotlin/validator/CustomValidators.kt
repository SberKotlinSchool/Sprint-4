package validator

import validation.ExactLengthStringValidation
import validation.MatchRegexStringValidation
import validation.NotEmptyStringValidation

class PhoneValidator(data: String?) : Validator<String>(data) {
    override fun prepare() {
        addValidation(NotEmptyStringValidation())
        addValidation(ExactLengthStringValidation(11))
        addValidation(MatchRegexStringValidation("[7|8]\\d+"))
    }
}


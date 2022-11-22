import kotlin.math.E

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {

    companion object {
        private val phoneValidatorRegex = "^[7|8]+[0-9]{10}$".toRegex()
    }

    override fun validate(value: String?): List<ErrorCode> {

        return when (value?.matches(phoneValidatorRegex)) {
            true -> listOf()
            else -> listOf(ErrorCode.INVALID_PHONE_NUMBER)
        }
    }
}

class SnilsValidator : Validator<String>() {

    companion object {
        private val snilsValidatorRegex = "^[0-9]{11}$".toRegex()
    }

    override fun validate(value: String?): List<ErrorCode> {


        return when (value?.matches(snilsValidatorRegex)) {
            true -> listOf()
            else -> listOf(ErrorCode.INVALID_SNILS_NUMBER)
        }
    }
}

class EmailValidator : Validator<String>() {

    companion object {
        private val emailValidatorRegex = "(?=.{5,32}\$)[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]{2,4}$".toRegex()
    }

    override fun validate(value: String?): List<ErrorCode> {

        return when (value?.matches(emailValidatorRegex)) {
            true -> listOf()
            else -> listOf(ErrorCode.INVALID_EMAIL)
        }
    }
}

class FirstNameValidator : Validator<String>() {

    companion object {
        private val nameValidatorRegex = "[а-яА-ЯёЁ]{1,16}".toRegex()
    }

    override fun validate(value: String?): List<ErrorCode> {


        return when (value?.matches(nameValidatorRegex)) {
            true -> listOf()
            else -> listOf(ErrorCode.INVALID_NAME)
        }
    }
}

class LastNameValidator : Validator<String>() {

    companion object {
        private val SurnameValidatorRegex = "[а-яА-ЯёЁ]{1,16}".toRegex()
    }

    override fun validate(value: String?): List<ErrorCode> {

        return when (value?.matches(SurnameValidatorRegex)) {
            true -> listOf()
            else -> listOf(ErrorCode.INVALID_SURNAME)
        }
    }
}


fun main() {


    val phoneValidator = PhoneValidator()
    val snilsValidator = SnilsValidator()
    val emailValidator = EmailValidator()
    val firstNameValidator = FirstNameValidator()

    val phoneNumber: String = "79111111111"
    val snilsNumber: String = "22222222222"
    val email: String = "test@gmail.com"
    val name : String = "Ивановфыфывфывфы"

    println("===========================================================")
    println("Валидируем телефон:")
    println("Длинна телефона ${phoneNumber.length}")
    println("Результат валидации ${phoneValidator.validate(phoneNumber)}")
    println("===========================================================")

    println("===========================================================")
    println("Валидируем СНИЛС:")
    println("Длинна СНИЛС ${snilsNumber.length}")
    println("Результат валидации ${snilsValidator.validate(snilsNumber)}")
    println("===========================================================")

    println("===========================================================")
    println("Валидируем e-mail:")
    println("Длинна e-mail ${email.length}")
    println("Результат валидации ${emailValidator.validate(email)}")
    println("===========================================================")

    println("===========================================================")
    println("Валидируем имя")
    println("Длинна имени ${name.length}")
    println("Результат валидации ${firstNameValidator.validate(name)}")
    println("===========================================================")
}
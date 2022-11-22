import ErrorCode.*
import java.util.function.Predicate


abstract class Validator<T> {
    abstract fun validate(value: T?): MutableList<ErrorCode>
}

open class StringValidator(val stringPattern: String, val lengthPredicate: Predicate<Int>) : Validator<String>() {

    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors : MutableList<ErrorCode> = ArrayList()
        if (value == null) {
            return errors.also { it.add(NULL_STRING) }
        }
        if (!value.matches(Regex(stringPattern))) {
            errors.add(INVALID_CHARACTER)
        }
        if (!lengthPredicate.test(value.length)) {
            errors.add(INVALID_LENGTH)
        }
        return errors
    }

}

class PhoneValidator : StringValidator(stringPattern = "^[78]\\d+\$", lengthPredicate =  { it == 11 })

class NameValidator : StringValidator(stringPattern = "^[А-Яа-я]+\$", lengthPredicate = { it <= 16 })

class EmailValidator : StringValidator(stringPattern = "^[A-Za-z0-9.@]+\$", lengthPredicate = {it <= 32 }) {

    private val domainRegex = Regex("[@][A-za-z]+[.][A-za-z]+\$")

    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        if (errors.isNotEmpty()) {
            return errors
        }
        if (!value!!.contains(domainRegex)) {
            errors.add(DOMAIN_NOT_FOUND)
        }
        return errors
    }
}

class SnilsValidator : StringValidator(stringPattern = "^\\d{11}\$", lengthPredicate =  Predicate<Int> { it == 11 }) {

    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        if (errors.isNotEmpty()) {
            return errors
        }
        val snilsWithControlNum = value!!.toLong()
        val controlNumFromStr = (snilsWithControlNum % 100).toInt()
        val snils = snilsWithControlNum / 100
        if (snils < 1001998L) {
            return errors
        }
        val snilsIntArr = snils.toString().map { it.toString().toInt() }
        val controlNumCalc = snilsIntArr.reversed().reduceIndexed { index, acc, i -> acc + (index+1) * i } % 101 % 100
        if (controlNumFromStr != controlNumCalc) {
            errors.add(INVALID_CONTROL_NUM)
        }
        return errors
    }

}

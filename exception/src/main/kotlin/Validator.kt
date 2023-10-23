import java.util.function.Predicate

abstract class Validator<T> {
    abstract fun validate(value: T?): MutableList<ErrorCode>
}

open class StringValidator(private val pattern: String, private val lengthPredicate: Predicate<Int>) :
    Validator<String>() {
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors: MutableList<ErrorCode> = ArrayList()
        if (value == null) {
            return errors.apply { add(ErrorCode.NULL_INPUT) }
        }
        if (!lengthPredicate.test(value.length)) {
            errors.add(ErrorCode.INVALID_LENGTH)
        }
        if (!value.matches(Regex(pattern))) {
            errors.add(ErrorCode.INVALID_CHARACTER)
        }
        return errors
    }
}

class NameValidator : StringValidator("^[А-Яа-я]+\$", { it <= 16 })

class PhoneValidator : StringValidator("^[78]\\d+\$", { it == 11 })

class EmailValidator : StringValidator("^[A-Za-z0-9.@]+\$", { it <= 32 }) {
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        val domainRegex = Regex("[^@]+@[A-Za-z]+[.][A-Za-z]+")
        if (!value!!.matches(domainRegex)) {
            errors.add(ErrorCode.INVALID_DOMAIN)
        }
        return errors
    }
}

class SnilsValidator : StringValidator("^\\d+$", { it == 11 }) {
    override fun validate(value: String?): MutableList<ErrorCode> {
        val errors = super.validate(value)
        /*
        Проверка контрольного числа Страхового номера проводится только для номеров больше номера 001-001-998
        Каждая цифра СНИЛС умножается на номер своей позиции (позиции отсчитываются с конца, то есть, справа)
        Полученные произведения суммируются
        Получить остаток от деления на 101
        Если получилось 100, контрольное число равно 0
         */
        val snilsWithControlNum = value!!.toLong()
        val snils = (snilsWithControlNum / 100).toInt()
        if (snils <= 1001998) {
            return errors
        }
        val controlNum = (snilsWithControlNum % 100).toInt()
        val snilsIntList = snils.toString().map { it.toString().toInt() }
        val calcControlNum =
            snilsIntList.reversed().reduceIndexed { i, calc, element -> calc + (i + 1) * element } % 101 % 100
        if (controlNum != calcControlNum) {
            errors.add(ErrorCode.INVALID_CONTROL_NUMBER)
        }
        return errors
    }
}
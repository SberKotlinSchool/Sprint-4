abstract class Validator<T> {

    /** Тип поля (для вывода ошибок) */
    abstract val type: String

    /** Валидация, возвращающая мапу "Тип поля - Список ошибок" или пустую мапу, если ошибок нет */
    fun validate(value: T?): Map<String, List<Error>> {
        return if (value != null) {
            val errors = getErrors(value)
            if (errors.isNotEmpty()) { mapOf(type to errors) } else { mapOf() }
        } else {
            mapOf(type to listOf())
        }
    }

    /** Валидация, дописывающая в имеющуюся мапу "Тип поля - Список ошибок" найденные ошибки, если они были */
    fun validate(value: T?, typeAndErrors: Map<String, List<Error>>): Map<String, List<Error>> {
        return if (value != null) {
            val errors = getErrors(value)
            if (errors.isNotEmpty()) { typeAndErrors.plus(type to errors) } else { typeAndErrors }
        } else {
            typeAndErrors
        }
    }

    /** Непосредственно валидация поля по правилам */
    abstract fun getErrors(value: T) : List<Error>
}

class PhoneValidator : Validator<String>() {
    override val type = "Phone"
    private val length = 11

    override fun getErrors(value: String): List<Error> {
        val errors = ArrayList<Error>(3)
        // длина 11 знаков ровно
        if (value.isBlank()) {
            errors.add(NumberError(0, length, ErrorCode.LENGTH_TOO_SHORT))
        } else {
            // только пройдя проверку isBlank
            if (value.length != length) {
                errors.add(NumberError(value.length, length, ErrorCode.LENGTH_NOT_EQUAL))
            } else {
                // Только для номеров корректной длины
                // начинаются с 7 или 8
                if (!Regex("^[7|8]").matches(value.subSequence(0, 1))) {
                    errors.add(NotParametrizedError(ErrorCode.PHONE_WRONG_FIRST_NUMBER))
                }
                // только цифры
                if (!Regex("[\\d]{10}$").matches(value.subSequence(1, value.length))) {
                    errors.add(NotParametrizedError(ErrorCode.INVALID_CHARACTERS))
                }
            }
        }
        return errors
    }
}

open class NameValidator : Validator<String>() {
    override val type = "Name"
    private val minLength = 1
    private val maxLength = 16

    override fun getErrors(value: String) : List<Error> {
        val errors = ArrayList<Error>(4)
        // длина слова - от 1 до 16 символов
        if (value.isBlank()) {
            errors.add(NumberError(0, minLength, ErrorCode.LENGTH_TOO_SHORT))
        } else {
            // Остальные проверки - только для не пустых строк
            if (value.length > maxLength) {
                errors.add(NumberError(value.length, maxLength, ErrorCode.LENGTH_TOO_LONG))
            }
            // первая буква - заглавная, кириллица.
            if (!Regex("^[А-Я]").matches(value.subSequence(0, 1))) {
                errors.add(NotParametrizedError(ErrorCode.NAME_WRONG_FIRST_LETTER))
            }
            // последующие буквы - прописные, кириллица
            if (!Regex("^[а-я]{0,15}$").matches(value.subSequence(1, value.length))) {
                errors.add(NotParametrizedError(ErrorCode.INVALID_CHARACTERS))
            }
        }
        return errors
    }
}

class SurnameValidator : NameValidator() {
    override val type = "Surname"
}

class EmailValidator : Validator<String>() {
    override val type = "Email"
    private val minLength = 6
    private val maxLength = 32

    override fun getErrors(value: String): List<Error> {
        val errors = ArrayList<Error>(2)
        if (value.isBlank()) {
            errors.add(NumberError(0, minLength, ErrorCode.LENGTH_TOO_SHORT))
        } else {
            // Остальные проверки - только для не пустых строк
            if (value.length < minLength) {
                errors.add(NumberError(value.length, minLength, ErrorCode.LENGTH_TOO_SHORT))
            } else {
                // Остальные проверки - только для строк корректной длины
                // длина не более 32 знаков
                if (value.length > maxLength) {
                    errors.add(NumberError(value.length, maxLength, ErrorCode.LENGTH_TOO_LONG))
                }
                // латиница + формат с валидацией @имя_домена
                if (!Regex("^[a-z]*@[a-z]*[.][a-z]{2,4}$").matches(value)) {
                    errors.add(NotParametrizedError(ErrorCode.EMAIL_FORMAT))
                }
            }
        }
        return errors
    }
}

//только цифры, 11 символов, с валидацией Контрольного числа
class SnilsValidator : Validator<String>() {
    override val type = "Snils"
    private val length = 11
    private val minCheckStr = "001001998"

    override fun getErrors(value: String): List<Error> {
        val errors = ArrayList<Error>(3)
        if (value.isBlank()) {
            errors.add(NumberError(0, length, ErrorCode.LENGTH_TOO_SHORT))
        } else {
            // Остальные проверки - только для не пустых строк
            // длина 11 знаков ровно
            if (value.length != length) {
                errors.add(NumberError(value.length, length, ErrorCode.LENGTH_NOT_EQUAL))
            } else {
                // Только для строки корректной длины
                // только цифры
                if (!Regex("^[\\d]{11}$").matches(value)) {
                    errors.add(NotParametrizedError(ErrorCode.INVALID_CHARACTERS))
                } else {
                    // проверка контрольного числа - только если прошла предыдущая проверка
                    // для чисел не меньше minCheckStr
                    if (value.subSequence(0, 9).toString().toInt() > minCheckStr.toInt()) {
                        val controlNumber = value.subSequence(9, 11).toString().toInt()
                        var controlSum = 0
                        for (i in 0..8) {
                            controlSum += value[i].digitToInt() * (9 - i)
                        }
                        val calculatedControlNum = if (controlSum < 101) {
                            controlSum
                        } else {
                            (controlSum % 101)
                        }
                        if (calculatedControlNum != controlNumber) {
                            errors.add(NotParametrizedError(ErrorCode.SNILS_CONTROL_SUM))
                        }
                    }
                }
            }

        }
        return errors
    }
}
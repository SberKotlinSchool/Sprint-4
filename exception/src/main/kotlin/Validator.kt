import java.util.regex.Pattern
import java.util.stream.IntStream

abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}

class PhoneValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        if (value != null && listOf('7','8').indexOf(value[0]) == -1) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
        }
        if (value != null && !Pattern.matches("[0-9]+", value)) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
        }
        if (value != null && value.length != 11) {
            errorList.add(ErrorCode.INVALID_LENGTH)
        }
        return errorList
    }
}

class NameValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        if (value != null && value.length > 32) {
            errorList.add(ErrorCode.EXCEEDED_LENGTH)
        }
        if (value != null && !Pattern.matches("[а-яА-Я]+", value)) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
        }
        return errorList
    }
}

class EmailValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        if (value != null && value.length > 32) {
            errorList.add(ErrorCode.EXCEEDED_LENGTH)
        }
        if (value != null && !Pattern.matches("[a-zA-Z0-9_.-]+@[a-zA-Z]+\\.[a-zA-Z]+", value))  {
            errorList.add(ErrorCode.INVALID_FORMAT)
        }
        return errorList
    }
}

class SnilsValidator : Validator<String>() {
    override fun validate(value: String?): List<ErrorCode> {
        val errorList = ArrayList<ErrorCode>()
        if (value != null && value.length != 11) {
            errorList.add(ErrorCode.INVALID_LENGTH)
        }
        if (value != null && !Pattern.matches("[0-9]+", value)) {
            errorList.add(ErrorCode.INVALID_CHARACTER)
        }
        if (value != null && !checkSum(value)) {
            errorList.add(ErrorCode.INVALID_CHECK_SUM)
        }
        return errorList
    }
}

fun checkSum(value: String?):Boolean {
    if (value != null) {
        var sum = IntStream.rangeClosed(0, 8).mapToObj { value[it].digitToInt() * (9 - it) }.reduce { t, u -> t + u }.get()
        if (sum > 101) {
            sum %= 101
        } else if (sum == 100 || sum == 101) {
            sum = 0
        }
        return sum == value.substring(9).toInt()
    }
    return false
}
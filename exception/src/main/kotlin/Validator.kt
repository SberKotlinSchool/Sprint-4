abstract class Validator<T> {
    abstract fun validate(value: T?): List<ErrorCode>
}


class FirstNameValidator: Validator<String>() {

    override fun validate(value: String?): MutableList<ErrorCode> {
        val errorLists: MutableList<ErrorCode> = mutableListOf()
        if (value == null) {
            errorLists.add(ErrorCode.INVALID_NULL_F)
        }
        else {
            if (!checkmatch(choice = 1, string = value)) {
                errorLists.add(ErrorCode.INVALID_FIRSTNAME)
            }
            if (value.length > 16) {
                errorLists.add(ErrorCode.INVALID_COUNT_CHAR_F)
            }
        }
        return errorLists
    }
}

class LastNameValidator: Validator<String>() {

    override fun validate(value: String?): MutableList<ErrorCode> {
        val errorLists: MutableList<ErrorCode> = mutableListOf()
        if (value == null) {
            errorLists.add(ErrorCode.INVALID_NULL_L)
        }
        else {
            if (!checkmatch(choice = 1, string = value)) {
                errorLists.add(ErrorCode.INVALID_LASTTNAME)
            }
            if (value.length > 16) {
                errorLists.add(ErrorCode.INVALID_COUNT_CHAR_L)
            }
        }
        return errorLists
    }
}

class PhoneValidator: Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        val errorLists: MutableList<ErrorCode> = mutableListOf()
        if (value == null) {
            errorLists.add(ErrorCode.INVALID_NULL_N)
        }
        else {
            if (!checkmatch(choice = 2, string = value)) {
                errorLists.add(ErrorCode.INVALID_NUMBER_PHONE)
            }
            if (value.first() !in listOf('7', '8')) {
                errorLists.add(ErrorCode.INVALID_START_NUMBER)
            }
            if (value.length != 11) {
                errorLists.add(ErrorCode.INVALID_COUNT_CHAR_N)
            }
        }
        return errorLists
    }
}
class MailValidator: Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        val errorLists: MutableList<ErrorCode> = mutableListOf()
        if (value == null) {
            errorLists.add(ErrorCode.INVALID_NULL_D)
        }
        else {
            if (!checkmatch(string = value)) {
                errorLists.add(ErrorCode.INVALID_MAILNAME)
            }
            if (!checkmatch(choice = 3, string = value)) {
                errorLists.add(ErrorCode.INVALID_DOMEN)
            }
            if (value.length > 32) {
                errorLists.add(ErrorCode.INVALID_COUNT_CHAR_D)
            }
        }
        return errorLists
    }
}
class SNILSValidator: Validator<String>(){
    override fun validate(value: String?): List<ErrorCode> {
        val errorLists: MutableList<ErrorCode> = mutableListOf()
        if (value == null) {
            errorLists.add(ErrorCode.INVALID_NULL_S)
        }
        else {
            if (!checkmatch(choice = 2, string = value)) {
                errorLists.add(ErrorCode.INVALID_SNILS_NUMBER)
            } else {
                val listCS: MutableList<Int> = mutableListOf()
                value.substring(0, value.length - 2).reversed()
                    .forEachIndexed { index, numbers -> listCS.add(numbers.toString().toInt() * (index + 1)) }
                val testCS: Int = listCS.sum()
                val withoutCS: Int = value.substring(value.length - 2, value.length).toInt()
                if (testCS < 100 && testCS != withoutCS) {
                    errorLists.add(ErrorCode.INVALID_CONTROL)
                } else if (testCS in listOf(100, 101) && withoutCS != 0) {
                    errorLists.add(ErrorCode.INVALID_CONTROL)
                } else if (testCS > 101 && testCS % 101 % 100 != withoutCS) {
                    errorLists.add(ErrorCode.INVALID_CONTROL)
                }
            }
            if (value.length != 11) {
                errorLists.add(ErrorCode.INVALID_COUNT_CHAR_S)
            }
        }
        return errorLists
    }
}


private fun checkmatch(choice: Int = 0, string: String): Boolean {
    /*
    Выбираем тип Алфавита:
        choice = 0 - латиница под почту (по умолчанию);
        choice = 1 - кириллица;
        choice = 2 - цифры;
        choice = 3 - структура наименования почты;
        string = проверяемая строка
     */
    var types = "[a-zA-Z0-9@._-]+$".toRegex()
    if(choice == 1) { types = "^[а-яА-Я]+$".toRegex() }
    else if (choice == 2) { types = "^[0-9]+$".toRegex() }
    else if (choice == 3) { types = "^[а-яА-Яa-zA-Z0-9]+@[а-яА-Яa-zA-Z0-9]+[.]{1}[а-яА-Яa-zA-Z0-9]{1,3}$".toRegex() }
    return string.matches(types)
}
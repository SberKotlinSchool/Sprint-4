fun String?.validatePhone(): List<ErrorCode> {
        val list = mutableListOf<ErrorCode>()
        this?.let {
            if (!"^[78]\\d*\$".toRegex().matches(it))
                list.add(ErrorCode.INVALID_CHARACTER)
            if (it.length != 11)
                list.add(ErrorCode.INVALID_LENGTH)
        } ?: list.add(ErrorCode.NULL_VALUE)
        return list
    }
 fun String?.validateName(): List<ErrorCode> {
        val list = mutableListOf<ErrorCode>()
        this?.let {
            if (!"^[а-яА-Я]*\$".toRegex().matches(it))
                list.add(ErrorCode.INVALID_CHARACTER)
            if (it.isEmpty() || it.length > 16)
                list.add(ErrorCode.INVALID_LENGTH)
        } ?: list.add(ErrorCode.NULL_VALUE)
        return list
    }
fun String?.validateMail(): List<ErrorCode> {
    val list = mutableListOf<ErrorCode>()
    this?.let {
        if (!"^[a-zA-z]+@[a-zA-z]+\\.[a-zA-z]+\$".toRegex().matches(it))
            list.add(ErrorCode.INVALID_CHARACTER)
        if (it.isEmpty() || it.length > 32)
            list.add(ErrorCode.INVALID_LENGTH)
    } ?: list.add(ErrorCode.NULL_VALUE)
    return list
}
 fun String?.validateSnils(): List<ErrorCode> {
    val list = mutableListOf<ErrorCode>()
    this?.let {
        if (!"^\\d*\$".toRegex().matches(it))
            list.add(ErrorCode.INVALID_CHARACTER)
        if (it.length != 11)
            list.add(ErrorCode.INVALID_LENGTH)
        if ("^\\d*\$".toRegex().matches(it) && it.length == 11) {
            val sum = this
                    .substring(0, it.length - 2)
                    .toCharArray()
                    .map { numb -> numb.digitToInt() }
                    .reversed()
                    .foldIndexed(0) { idx, sum, element -> (idx + 1) * element + sum }
            val controlNumber = it.substring(it.length - 2, it.length)
            if ((sum < 100 && controlNumber != sum.toString()) ||
                    (sum == 100 && controlNumber != "00") ||
                    (sum > 100 && ((sum % 101 == 100 && controlNumber != "00") ||
                            (controlNumber != (sum % 101).toString()))))
                list.add(ErrorCode.INVALID_CONTROL_NUMBER)
        }
    } ?: list.add(ErrorCode.NULL_VALUE)
    return list
}
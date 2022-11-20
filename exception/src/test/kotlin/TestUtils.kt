fun <R> getField(instance: Any, propertyName: String): R {
    val field = instance.javaClass.getDeclaredField(propertyName)
    field.isAccessible = true
    return field.get(instance) as R
}

fun <R> setField(instance: Any, propertyName: String, value: R) {
    val field = instance.javaClass.getDeclaredField(propertyName)
    field.isAccessible = true
    field.set(instance, value)
}


fun generateCorrectSnils(nineDigitsString: String) : String {
    return nineDigitsString + calculateControlNumber(nineDigitsString)
}

fun generateIncorrectSnils(nineDigitsString: String) : String {
    val correctControlNumber = calculateControlNumber(nineDigitsString)
    val strBuilder = StringBuilder(nineDigitsString).append(correctControlNumber[0])
    // подмена второго знака для создания фейкового контрольного числа
    val digit = correctControlNumber[1].digitToInt()
    if (digit > 5) { strBuilder.append(digit - 1) } else { strBuilder.append( digit + 1) }
    return strBuilder.toString()
}

private fun calculateControlNumber(nineDigitsString: String) : String {
    // проверка на 9 знаков на входе
    if (nineDigitsString.isBlank() || nineDigitsString.length != 9) {
        IllegalArgumentException("SnilsGenerator - Incorrect input")
    }
    var controlSum = 0
    for (i in 0..8) {
        controlSum += nineDigitsString[i].digitToInt() * (9 - i)
    }
    val calculatedControlNum = if (controlSum < 101) {
        controlSum.toString()
    } else {
        (controlSum % 101).toString()
    }
    val correctControlNum = if (calculatedControlNum.length > 2) {
        calculatedControlNum.subSequence(0, 2)
    } else if (calculatedControlNum.length == 1) {
        "0$calculatedControlNum"
    } else {
        calculatedControlNum
    }
    return correctControlNum.toString()
}
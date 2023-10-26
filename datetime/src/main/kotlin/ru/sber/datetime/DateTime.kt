package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// 1. Получить сет часовых поясов, которые используют смещение от UTC не в полных часах.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val resultSet = mutableSetOf<String>()
    val ids = ZoneId.getAvailableZoneIds()
    val regex = """^[+-][\d]{2}:[\d]{2}$""".toRegex()
    //Будем искать часовые пояса, актуальные на эту дату. Историческая справка: В Сингапуре был часовой пояс GMT+07:30 до 1981 года, а после стал GMT+08:00
    val instant = LocalDateTime.of(2023, 1, 1, 0, 0).toInstant(ZoneOffset.UTC)

    for (id in ids) {
        val textOffset = ZoneId.of(id).rules.getStandardOffset(instant).id //Формат "+03:30"

        if (regex.containsMatchIn(textOffset) && textOffset.substring(textOffset.length - 2, textOffset.length) != "00") {
            resultSet.add(id)
        }

    }
    return resultSet
}

// 2. Для заданного года вывести список, каким днем недели был последний день в месяце.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val days = mutableListOf<String>()
    for (month in 1..12) {
        days.add(YearMonth.of(year, month).atEndOfMonth().dayOfWeek.toString())
    }

    return days
}

// 3. Для заданного года вывести количество дней, выпадающих на пятницу 13-ое.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var counter = 0
    for (month in 1..12) {
        if (LocalDate.of(year, month, 13).dayOfWeek == DayOfWeek.FRIDAY) {
            counter++
        }
    }
    return counter
}

// 4. Вывести заданную дату в формате "01 Aug 2021, 23:39", в котором дата локализована для вывода в США (US).
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US).format(dateTime)
}




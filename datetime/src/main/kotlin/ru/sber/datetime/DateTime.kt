package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*


// 1. Получить сет часовых поясов, которые используют смещение от UTC не в полных часах.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    println("\nСписок часовых поясов, которые используют смещение от UTC не в полных часах:")
    return ZoneId.getAvailableZoneIds()
        .asSequence()
        .map { it to ZonedDateTime.of(LocalDateTime.now(), ZoneId.of(it)).offset.totalSeconds % 3600 }
        .filter { (k, v) -> v != 0 }
        .onEach { (k, v) -> println("$k - ${(v.toDouble()/3600)}") }
        .map { (k, v) -> k }
        .toSet()
}

// 2. Для заданного года вывести список, каким днем недели был последний день в месяце.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    println("\nCписок для $year года, каким днем недели был последний день в месяце: ")
    return (1..12)
        .map { LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString() }
        .onEach { println(it) }
        .toList()
}

// 3. Для заданного года вывести количество дней, выпадающих на пятницу 13-ое.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    val count = (1..12)
        .map { LocalDate.of(year, it, 13).dayOfWeek }
        .count { it == DayOfWeek.FRIDAY }
    println("\nКоличество дней, выпадающих на пятницу 13-ое, в $year году, равно $count")
    return count
}

// 4. Вывести заданную дату в формате "01 Aug 2021, 23:39", в котором дата локализована для вывода в США (US).
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val formatted = dateTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, HH:mm").localizedBy(Locale.US)).toString()
    println("\nДата $dateTime в формате '01 Aug 2021, 23:39', локализованная для вывода в США (US): $formatted")
    return formatted
}

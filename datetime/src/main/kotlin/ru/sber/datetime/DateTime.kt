package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

/**
 * Получить сет часовых поясов, которые используют смещение от UTC не в полных часах.
 */
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .map { TimeZone.getTimeZone(it) }
        .filter { (it.rawOffset / 3600 % 1000) != 0 }
        .map { it.id }.toSet()
}

/**
 * Для заданного года вывести список, каким днем недели был последний день в месяце.
 */
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val array = mutableListOf<String>()
    for (i in 1..12) {
        val date = LocalDate.of(year, i, 1)
        val lastDayOfMonth = date.with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.name
        array.add(lastDayOfMonth)
    }
    return array
}

/**
 * Для заданного года вывести количество дней, выпадающих на пятницу 13-ое.
 */
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var count = 0
    for (i in 1..12) {
        val date = LocalDate.of(year, i, 13)
        if (date.dayOfWeek === (DayOfWeek.FRIDAY)) count++
    }
    return count
}

/**
 * Вывести заданную дату в формате "01 Aug 2021, 23:39", в котором дата локализована для вывода в США (US).
 */
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm", Locale.US))
}




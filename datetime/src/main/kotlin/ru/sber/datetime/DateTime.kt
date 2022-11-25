package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1) Получить сет часовых поясов, которые используют смещение от UTC не в полных часах.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .filter { TimeZone.getTimeZone(it).rawOffset % (60 * 60 * 1000) != 0 }
        .toSet()
}

// 2) Для заданного года вывести список, каким днем недели был последний день в месяце.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return java.time.Month.values().map {
        LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString()
    }
}

// 3) Для заданного года вывести количество дней, выпадающих на пятницу 13-ое.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return java.time.Month.values().map { LocalDate.of(year, it, 13).dayOfWeek }.filter { it == DayOfWeek.FRIDAY }.size
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").localizedBy(Locale.US))
}


package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter { TimeZone.getTimeZone(it).rawOffset % (1000 * 60 * 60) != 0}.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values().map { LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString() }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values().map {LocalDate.of(year, it, 13).dayOfWeek}.filter { it == DayOfWeek.FRIDAY }.size
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").localizedBy(Locale.US))
}




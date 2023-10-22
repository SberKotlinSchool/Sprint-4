package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .filter { (TimeZone.getTimeZone(it).rawOffset) % 3600000 != 0 }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return (1..12)
        .map {
            LocalDate.of(year, it, 1)
                .with(TemporalAdjusters.lastDayOfMonth())
                .dayOfWeek.toString()
        }
        .toList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return (1..12)
        .map { LocalDate.of(year, it, 13).dayOfWeek }
        .count { DayOfWeek.FRIDAY == it }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String =
    dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US))



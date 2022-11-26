package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale
import java.util.TimeZone

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .map { TimeZone.getTimeZone(it) }
        .filter { (it.rawOffset / 3600 % 1000) != 0 }
        .map { it.id }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values().map { LocalDate.of(year, it, 1) }
        .map { it.with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.name }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values().map { LocalDate.of(year, it, 13) }
        .count { it.dayOfWeek == DayOfWeek.FRIDAY }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US))
}




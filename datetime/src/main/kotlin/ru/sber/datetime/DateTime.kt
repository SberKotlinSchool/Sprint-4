package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters.lastDayOfMonth
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .filter { TimeZone.getTimeZone(it).rawOffset % (60 * 60 * 1000) != 0 }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values()
        .map { LocalDate.of(year, it, 13).with(lastDayOfMonth()).dayOfWeek.name }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values()
        .count { LocalDate.of(year, it, 13).dayOfWeek.equals(DayOfWeek.FRIDAY) }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime
        .format(
            DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm")
                .withLocale(Locale.US)
        )
}




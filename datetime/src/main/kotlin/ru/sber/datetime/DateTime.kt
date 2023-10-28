package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter {
        (TimeZone.getTimeZone(it).rawOffset % (1000 * 60 * 60)) != 0
    }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values().map {
        LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString()
    }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values().filter {
        LocalDate.of(year, it, 13).dayOfWeek == DayOfWeek.FRIDAY
    }.size
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM uuuu, HH:mm", Locale.ENGLISH))
}




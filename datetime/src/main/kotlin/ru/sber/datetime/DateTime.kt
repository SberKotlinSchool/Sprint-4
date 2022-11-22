package ru.sber.datetime

import java.time.*
import java.time.temporal.TemporalAdjusters
import java.time.format.DateTimeFormatter
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val availableZones = ZoneId.getAvailableZoneIds()
    return availableZones
        .map { TimeZone.getTimeZone(it) }
        .filter { it.rawOffset / 1000 % 3600 != 0 }
        .mapTo(HashSet()) { it.id }
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val monthValues = Month.values()
    return monthValues
        .map { LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString() }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    val monthValues = Month.values()
    return monthValues
        .map { LocalDate.of(year, it, 13) }
        .filter { it.dayOfWeek == DayOfWeek.FRIDAY  }
        .count()
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String = dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US))





package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import java.util.stream.Collectors

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().stream()
            .map { TimeZone.getTimeZone(it) }
            .filter { it.rawOffset % (1000 * 60 * 60) != 0 }
            .map { it.toZoneId().id }
            .collect(Collectors.toSet())
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values().map { month ->
        (TemporalAdjusters.lastDayOfMonth().adjustInto(LocalDate.of(year, month, 1)) as LocalDate).dayOfWeek.name
    }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values()
            .map { month -> LocalDate.of(year, month, 13).dayOfWeek.name }
            .count { it == "FRIDAY" }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val format = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
    return format.format(dateTime)
}




package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .filter {
            ZonedDateTime.now(ZoneId.of(it)).minute !=
                    ZonedDateTime.now(ZoneId.of("UTC")).minute
        }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values()
        .map { month ->
            LocalDateTime
                .of(year, month, 4, 4, 4)
                .with(TemporalAdjusters.lastDayOfMonth())
                .dayOfWeek
                .name
        }
        .toList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values()
        .filter { month ->
            LocalDateTime
                .of(year, month, 13, 1, 1)
                .dayOfWeek == DayOfWeek.FRIDAY
        }
        .count()
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(
        DateTimeFormatter
            .ofPattern("dd MMM yyyy, HH:mm")
            .withLocale(Locale.US)
    )
}




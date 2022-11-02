package ru.sber.datetime

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.TemporalAdjusters
import java.util.stream.Collectors
import java.util.stream.IntStream

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val instant = Instant.parse("2022-01-01T00:00:00.00Z")
    return ZoneId.getAvailableZoneIds()
        .filter { zoneIdName ->
            ZoneId.of(zoneIdName).rules.getOffset(instant).totalSeconds / 360 % 10 != 0
        }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val lastDayOfMonthAdjuster = TemporalAdjusters.lastDayOfMonth()

    return IntStream.range(1, 13)
        .mapToObj { month ->
            LocalDate.of(year, month, 1)
                .with(lastDayOfMonthAdjuster)
                .dayOfWeek
                .toString()
        }
        .collect(Collectors.toList())
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return 0
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return ""
}




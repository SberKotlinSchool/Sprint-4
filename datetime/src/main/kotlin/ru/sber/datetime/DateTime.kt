package ru.sber.datetime

import java.time.DayOfWeek
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale
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
    val nextFridayAdjuster = TemporalAdjusters.next(DayOfWeek.FRIDAY)
    var date = LocalDate.of(year, 1, 1)
    var thirteenFridaysCount = 0

    while (date.year == year) {
        date = date.with(nextFridayAdjuster)
        if (date.dayOfMonth == 13) {
            thirteenFridaysCount++
        }
    }

    return thirteenFridaysCount
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val dateTimePattern = "dd MMM yyyy, HH:mm"
    val dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern).withLocale(Locale.US)
    return dateTime.format(dateTimeFormatter).toString()
}




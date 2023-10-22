package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val instant = Instant.now()
    return ZoneId.getAvailableZoneIds().filter {
            ZoneId.of(it).rules.getOffset(instant)
                .totalSeconds % 3600 != 0 }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val last = TemporalAdjusters.lastDayOfMonth()

    return IntStream.range(1, 13)
        .mapToObj { LocalDate.of(year, it, 1)
            .with(last).dayOfWeek.toString() }
                .collect(Collectors.toList())
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    val friday = TemporalAdjusters.next(DayOfWeek.FRIDAY)
    var date = LocalDate.of(year, 1, 1)
    var count = 0

    while (date.year == year) {
        date = date.with(friday)
        if (date.dayOfMonth == 13)
            count++
    }

    return count
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
        .withLocale(Locale.US)).toString()
}




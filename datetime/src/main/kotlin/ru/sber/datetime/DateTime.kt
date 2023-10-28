package ru.sber.datetime

import java.time.DayOfWeek
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val now = LocalDateTime.now()
    val greenwichTime = ZonedDateTime.of(now, ZoneId.of("Etc/GMT-0"))
    return ZoneOffset.getAvailableZoneIds().filterTo(mutableSetOf()) {
        Duration.between(
            greenwichTime,
            ZonedDateTime.of(now, ZoneId.of(it))
        ).abs().toMinutes() % 60 != 0L
    }
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return generateSequence(LocalDate.of(year, 1, 31)) {
        it.plusDays(1).with(TemporalAdjusters.lastDayOfMonth())
    }
        .takeWhile { it.year == year }
        .map { it.dayOfWeek.name }
        .toList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return generateSequence(LocalDate.of(year, 1, 1)) {
        it.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
    }
        .takeWhile { it.year == year }
        .count { it.dayOfMonth == 13 }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm").withLocale(Locale.UK))
}




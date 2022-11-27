package ru.sber.datetime

import java.time.DayOfWeek.FRIDAY
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalDateTime.now
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.time.temporal.TemporalAdjusters.dayOfWeekInMonth
import java.util.*


// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter {
        ZoneId.of(it).rules.getOffset(now()).totalSeconds % (60 * 60) != 0
    }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val lastDayOfMonthAdjuster = TemporalAdjusters.lastDayOfMonth()
    return (1 .. 12)
        .map { LocalDate.of(year,it,1) }
        .map { lastDayOfMonthAdjuster.adjustInto(it)}
        .map { LocalDate.from(it).dayOfWeek.toString()}
        .toList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    val fta = dayOfWeekInMonth(1, FRIDAY)
    var d = LocalDate.from(fta.adjustInto(LocalDate.of(year, 1, 1)))
    var result = 0
    while (d.year <= year) {
        if (d.dayOfMonth == 13) {
            result++
        }
        d = d.plusDays(7)
    }
    return result
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val fmt = DateTimeFormatter.ofPattern("dd MMM u, HH:mm").withLocale(Locale.US)
    return dateTime.format(fmt)
}


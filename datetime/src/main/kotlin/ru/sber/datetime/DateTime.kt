package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.util.*

val INSTANT_TIME: Instant = Instant.parse("2022-10-30T10:00:00.00Z")
val LAST_DAY_OF_MONTH: TemporalAdjuster = TemporalAdjusters.lastDayOfMonth()
// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> = ZoneId.getAvailableZoneIds().filter {
    LocalDateTime.ofInstant(INSTANT_TIME, ZoneId.of(it)).minute > 0
}.toSet()

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> = (1..12).map {
    val date = LocalDate.of(year, it, 1).with(LAST_DAY_OF_MONTH)
    date.dayOfWeek.name
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int = (1..12).count {
        LocalDate.of(year, it, 13).dayOfWeek == DayOfWeek.FRIDAY
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String = dateTime.format(
    DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
)




package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

val INSTANT_TIME: Instant = Instant.parse("2022-10-30T10:00:00.00Z")

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> = ZoneId.getAvailableZoneIds().filter {
    LocalDateTime.ofInstant(INSTANT_TIME, ZoneId.of(it)).minute != 0
}.toSet()

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> = (0..11).map {
    val date = LocalDate.of(year, it + 1, 1)
    val d = date.with(TemporalAdjusters.lastDayOfMonth())
    d.dayOfWeek.name
}.toList()

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int = (0..11).filter {
    val date = LocalDate.of(year, it + 1, 13)
    date.dayOfWeek == DayOfWeek.FRIDAY
}.size

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String = dateTime.format(
    DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
)




package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.Month
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val SECONDS_IN_HOUR = 3_600
private val US_DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US)

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> =
    ZoneId.getAvailableZoneIds().sorted()
        .filterNot { ZoneId.of(it).rules.getOffset(LocalDateTime.now()).totalSeconds % SECONDS_IN_HOUR == 0 }
        .toSet()

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> =
    Month.values().map { month -> YearMonth.of(year, month).atEndOfMonth().dayOfWeek.name }

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int =
    Month.values().map { month -> YearMonth.of(year, month).atDay(13) }
        .count { it.dayOfWeek == DayOfWeek.FRIDAY }

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String =
    dateTime.format(US_DATE_FORMATTER)

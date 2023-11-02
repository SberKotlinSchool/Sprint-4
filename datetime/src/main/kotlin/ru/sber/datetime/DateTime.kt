package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> = ZoneId.getAvailableZoneIds().filter {
    ZoneId.of(it).rules.getOffset(LocalDateTime.now(ZoneOffset.UTC)).totalSeconds % 3600 != 0
}.toSet()

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> =
    (1..12).map { YearMonth.of(year, it).atEndOfMonth().dayOfWeek.toString() }

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int =
    (1..12).map { LocalDate.of(year, it, 13) }.count { it.dayOfWeek == DayOfWeek.FRIDAY }

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String = dateTime.format(
    DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(
        Locale.US
    )
)




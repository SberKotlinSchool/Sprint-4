package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Year
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> =
    ZoneId.getAvailableZoneIds()
        .filter { ZoneId.of(it).rules.getOffset(LocalDateTime.now()).totalSeconds % 3600 != 0 }
        .toSet()

// 2.
fun getLastInMonthDayWeekList(year: Int) =
    (1..12).map {
        LocalDate.from(
            TemporalAdjusters.lastDayOfMonth().adjustInto(Year.of(year).atMonth(it).atDay(1))
        ).dayOfWeek.toString()
    }.toList()

// 3.
fun getNumberOfFridayThirteensInYear(year: Int) = (1..12)
    .map {
        LocalDate.from(Year.of(year).atMonth(it).atDay(13)).dayOfWeek
    }.filter { it.equals(DayOfWeek.FRIDAY) }.count()

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime) =
    dateTime.format(DateTimeFormatter.ofPattern("dd MMM YYY, HH:mm").withLocale(Locale.US))



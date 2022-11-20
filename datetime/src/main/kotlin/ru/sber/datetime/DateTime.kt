package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*


// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> =
    ZoneId.getAvailableZoneIds().filter { TimeZone.getTimeZone(it).rawOffset % (1000 * 60 * 60) != 0 }.toSet()


// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> =
    Month.values().map { LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString() }


// 3.
fun getNumberOfFridayThirteensInYear(year: Int) =
    Month.values().count { LocalDate.of(year, it, 13).dayOfWeek == DayOfWeek.FRIDAY }


// 4.
fun getFormattedDateTime(dateTime: LocalDateTime) =
    DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US).format(dateTime)





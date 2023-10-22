package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset() =
        ZoneOffset.getAvailableZoneIds().filter { ((TimeZone.getTimeZone(it).rawOffset / 60000) % 60) != 0 }.toSet()


// 2.
fun getLastInMonthDayWeekList(year: Int) =
        (1..12).map { LocalDate.of(year, Month.of(it), 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString() }


// 3.
fun getNumberOfFridayThirteensInYear(year: Int) =
    (1..12).count { LocalDate.of(year, Month.of(it), 13).dayOfWeek == DayOfWeek.FRIDAY }

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime) =
        dateTime.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm", Locale.US))




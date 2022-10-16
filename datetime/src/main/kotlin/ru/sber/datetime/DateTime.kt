package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> =
    ZoneId.getAvailableZoneIds()
        .filter { TimeZone.getTimeZone(it).rawOffset / 1000 % 3600 != 0 }
        .toSet()

// 2.
fun getLastInMonthDayWeekList(year: Int) = Month.values().map {
        LocalDate
            .of(year, it, 1)
            .with(TemporalAdjusters.lastDayOfMonth())
            .dayOfWeek
            .toString()
    }

// 3.
fun getNumberOfFridayThirteensInYear(year: Int) = Month.values().map {
    LocalDate
        .of(year, it, 13)
        .dayOfWeek
    }
    .filter { it == DayOfWeek.FRIDAY }
    .size

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String =
    dateTime.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm", Locale.US))




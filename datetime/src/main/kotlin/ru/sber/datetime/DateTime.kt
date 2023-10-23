package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .filterTo(mutableSetOf()) {
            TimeZone.getTimeZone(it).rawOffset / 1000 % 3600 != 0
        }
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values().map { month ->
        val localDate = LocalDate.of(year, month, 1)
            .with(TemporalAdjusters.lastDayOfMonth())
        localDate.dayOfWeek.toString()
    }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values().count { month ->
        LocalDate.of(year, month, 13).dayOfWeek == DayOfWeek.FRIDAY
    }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val pattern = "dd MMM YYYY, HH:mm"
    val dateTimeFormatter = DateTimeFormatter.ofPattern(pattern).withLocale(Locale.US)
    return dateTime.format(dateTimeFormatter)
}




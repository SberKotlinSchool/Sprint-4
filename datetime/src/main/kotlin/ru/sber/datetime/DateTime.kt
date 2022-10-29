package ru.sber.datetime

import java.time.LocalDateTime
import java.time.*
import java.util.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val zones = mutableSetOf<String>()
    ZoneId.getAvailableZoneIds().forEach() {
        if (TimeZone.getTimeZone(it).rawOffset / 1000 % 3600 != 0)
            zones.add(it)

    }
    return zones
}

// 2.
fun getLastInMonthDayWeekList(year: Int) : List<String> {
    val result = mutableListOf<String>()
    Month.values().forEach {
        val day = LocalDate.of(year, it, 1)
            .with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString()
        result.add(day)
    }
    return result
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var count = 0
    Month.values().forEach {
        if (LocalDate.of(year, it, 13).dayOfWeek == DayOfWeek.FRIDAY)
            count++
    }
    return count
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm")
        .withLocale(Locale.US))
}




package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*
import kotlin.collections.HashSet

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
            .map { TimeZone.getTimeZone(it) }
            .filter { it.rawOffset / 1000 % 3600 != 0  }
            .mapTo(HashSet()) { it.id }

}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values().map { LocalDate.of(year, it, 1) }
            .map { it.with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString() }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values().map { LocalDate.of(year, it, 13) }
            .count { it.dayOfWeek == DayOfWeek.FRIDAY  }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US))
}




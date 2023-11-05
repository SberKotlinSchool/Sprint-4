package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
            .filter {
                val offsetMinutes = ZonedDateTime.now(ZoneId.of(it)).offset.totalSeconds / 60
                offsetMinutes % 60 != 0
            }
            .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return (1..12).map { month ->
        LocalDate.of(year, month, LocalDate.of(year, month, 1).lengthOfMonth())
                .dayOfWeek
                .toString()
    }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return (1..12).count { month ->
        LocalDate.of(year, month, 13).dayOfWeek == DayOfWeek.FRIDAY
    }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
    return dateTime.format(formatter)
}




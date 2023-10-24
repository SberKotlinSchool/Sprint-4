package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val filteredSet = ZoneId.getAvailableZoneIds()
        .filter {
            ZoneId.of(it).rules.getOffset(LocalDateTime.now(ZoneOffset.UTC)).totalSeconds % 3600 != 0
        }
        .toSet()

    return filteredSet
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val result = (1..12).map { month ->
        YearMonth.of(year, month).atEndOfMonth().dayOfWeek.toString()
    }
        .toList()

    return result
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    val result = (1..12).map { month ->
        LocalDate.of(year, month, 13)
    }
        .count { it.dayOfWeek == DayOfWeek.FRIDAY }
    return result
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US))
}




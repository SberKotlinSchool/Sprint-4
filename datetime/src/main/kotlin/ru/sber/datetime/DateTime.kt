package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId
        .getAvailableZoneIds()
        .filter {
            val id = ZoneId.of(it).rules.getOffset(LocalDateTime.now()).id
            !(id.contains("Z") || id.endsWith("00"))
        }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month
        .values()
        .map {
            DayOfWeek
                .from(LocalDate.of(year, it.value, 1).with(TemporalAdjusters.lastDayOfMonth()))
                .name
        }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month
        .values()
        .count {
            LocalDate.of(year, it.value, 13).dayOfWeek == DayOfWeek.FRIDAY
        }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US))
}




package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter {
        val id = ZoneId.of(it).rules.getOffset(LocalDateTime.now()).id
        !(id.contains("Z") || id.endsWith("00"))
    }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> = Month.values()
    .map { LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.name }.toList()


// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int = Month.values()
    .count { LocalDate.of(year, it, 13).dayOfWeek == DayOfWeek.FRIDAY }


// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
    return dateTime.format(formatter)
}




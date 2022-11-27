package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val secondsInHour = 3600
    return ZoneId.getAvailableZoneIds()
        .filterNot {
            val secondInZone = ZoneId.of(it)
                .rules
                .getOffset(LocalDateTime.now())
                .totalSeconds
            secondInZone % secondsInHour == 0
        }
        .sorted()
        .toSet()
}

fun main() {
    println(getZonesWithNonDivisibleByHourOffset())
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val list = mutableListOf<String>()
    val currentYear = Year.of(year)

    for (month in 1..12) {
        val lengthOfMonth = currentYear.atMonth(month).lengthOfMonth()
        list.add(LocalDate.of(year, month, lengthOfMonth).dayOfWeek.name)
    }
    return list
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var countDays = 0
    for (month in 1..12) {
        val dayOfWeek = LocalDate.of(year, month, 13).dayOfWeek
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            countDays++
        }
    }
    return countDays
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String = dateTime.format(
    DateTimeFormatter
        .ofPattern("dd MMM yyyy, HH:mm")
        .withLocale(Locale.US)
)

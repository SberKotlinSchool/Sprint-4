package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .filterNot {
            val secondsInZone = ZoneId.of(it).rules.getOffset(LocalDateTime.now()).totalSeconds
            secondsInZone % 3600 == 0
        }
        .sorted()
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val listOfDays = mutableListOf<String>()
    val currentYear = Year.of(year)
    for (month in 1..12) {
        val dayInMonth = currentYear.atMonth(month).lengthOfMonth()
        listOfDays.add(LocalDate.of(year, month, dayInMonth).dayOfWeek.name)
    }
    return listOfDays
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var daysCount = 0
    for (month in 1..12) {
        val dayOfWeek = LocalDate.of(year, month, 13).dayOfWeek
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            daysCount++
        }
    }
    return daysCount
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.ENGLISH)
    return dateTime.format(formatter)
}




package ru.sber.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*


// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val localDateTime = LocalDateTime.now();
    return ZoneId.getAvailableZoneIds().filter {
        localDateTime.atZone(ZoneId.of(it)).offset.totalSeconds % 3600 != 0
    }.toSet()
}


// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val days = ArrayList<String>()
    for (i in 1..12){
        var startDate = LocalDate.of(year, i, 1)
        var lastDay = startDate.with(TemporalAdjusters.lastDayOfMonth());
        days.add(lastDay.dayOfWeek.name)
    }
    return days
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var numberOfFridayThirteens = 0
    for (i in 1..12){
        var day = LocalDate.of(year, i, 13)
        if (day.dayOfWeek.value == 5)
            numberOfFridayThirteens += 1
    }
    return numberOfFridayThirteens
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
    return dateTime.format(formatter)
}




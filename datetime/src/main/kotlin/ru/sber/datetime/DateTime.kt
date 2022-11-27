package ru.sber.datetime

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.temporal.TemporalAdjusters


// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return emptySet()
}

fun getLastInMonthDayWeekList(year: Int): List<String> {
    var daysList = arrayListOf<String>()

    enumValues<Month>().forEach {
        val localDate = LocalDate.of(year, it, 1)
        daysList.add(localDate.with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString())
    }
    return daysList
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return 0
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return ""
}




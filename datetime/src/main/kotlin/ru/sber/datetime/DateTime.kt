package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalAdjuster
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter { TimeZone.getTimeZone(it).rawOffset / 1000 % 3600 != 0 }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val list = mutableListOf<String>()
    val startYear = LocalDate.of(year, 1, 1)
    for (i in 0 until 12){
        val lastDayOfMonth = startYear.plus(i.toLong(), ChronoUnit.MONTHS).with(TemporalAdjusters.lastDayOfMonth())
        list.add(lastDayOfMonth.dayOfWeek.name)
    }
    return list
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var counter = 0
    for (month in Month.values()){
        if (LocalDate.of(year, month, 13).dayOfWeek == DayOfWeek.FRIDAY){
            counter++;
        }
    }
    return counter
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm").withLocale(Locale.US))
}




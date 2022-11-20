package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {

    return ZoneId.getAvailableZoneIds().filter { TimeZone.getTimeZone(it).rawOffset / 60 / 30 / 1000 % 2 != 0 }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val list: MutableList<String> = mutableListOf()
    var date = LocalDate.of(year, Month.JANUARY, 1)
    for (i in 1..12) {
        list.add(date.with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.name)
        date = date.plusMonths(1)
    }

    return list
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values().count { LocalDate.of(year, it, 13).dayOfWeek.equals(DayOfWeek.FRIDAY) }
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(
        DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm").withLocale(Locale.US)
    )
}




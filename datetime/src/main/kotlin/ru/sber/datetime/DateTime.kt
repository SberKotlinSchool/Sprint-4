package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Locale.US

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return  ZoneId.getAvailableZoneIds().filter { TimeZone.getTimeZone(it).rawOffset / 1000 % 3600 != 0  }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    var date: LocalDate
    val ans = ArrayList<String>()
    for (month in Month.values()) {
        date = LocalDate.of(year, month, 1)
        ans.add(date.plusMonths(1).minusDays(1).dayOfWeek.toString())
    }
    return ans
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var date: LocalDate
    var ans = 0
    for (month in Month.values()) {
        date = LocalDate.of(year, month, 13)
        if (date.dayOfWeek == DayOfWeek.FRIDAY) ans++
    }
    return ans
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime
        .format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(US))
}




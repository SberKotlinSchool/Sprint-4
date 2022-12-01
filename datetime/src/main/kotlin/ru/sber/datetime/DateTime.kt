package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset() =
    ZoneId.getAvailableZoneIds()
        .filter { TimeZone.getTimeZone(it).rawOffset % 3_600_000 != 0 }
        .toSet()


// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val result: MutableList<String> = mutableListOf()
    var date: LocalDate
    for (month in 1..12) {
        date = LocalDate.of(year, month, 10)
        result.add(date
            .with(TemporalAdjusters.lastDayOfMonth())
            .dayOfWeek
            .toString())
    }
    return result
}


// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var count = 0
    var date: LocalDate = LocalDate.of(year - 1, 12,31)

    do  {
        date = date.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
        if (date.dayOfMonth == 13) count++
    } while (date.isBefore(LocalDate.of(year + 1, 1,1)))
    return count
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String =
    dateTime.format (
        DateTimeFormatter
            .ofPattern("dd MMM yyyy, HH:mm")
            .withLocale(Locale.ENGLISH)
    )




package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val result: MutableSet<String> = mutableSetOf<String>()
    val zones = ZoneId.getAvailableZoneIds()

    zones.forEach {
        val offset = ZoneId.of(it).rules.getOffset(LocalDateTime.now())

        if (offset.totalSeconds % 3600 != 0) {
            println(it)
            result.add(it)
        }
    }

    return result
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val result = mutableListOf<String>()
    val localDateTime = LocalDateTime.now().withYear(year)

    for (i in 1..12) {
        val day = localDateTime
            .withMonth(i)
            .with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek
        result.add(day.toString())
    }

    return result
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var counter: Int = 0
    val localDateTime = LocalDateTime.now().withYear(year)

    for (i in 1..12) {
        if (localDateTime.withMonth(i).withDayOfMonth(13).dayOfWeek == DayOfWeek.FRIDAY)
            counter++
    }
    return counter
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime) =
    dateTime.format(DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm").withLocale(Locale.US))


fun main() {
    getZonesWithNonDivisibleByHourOffset()
}



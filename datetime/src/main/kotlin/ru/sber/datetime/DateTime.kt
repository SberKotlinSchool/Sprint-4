package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter {
        val zoneId = ZoneId.of(it)
        val zoneDateTime = Instant.now().atZone(zoneId)
        val secondsOfHour = zoneDateTime.offset.totalSeconds % (60 * 60)
        secondsOfHour != 0
    }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val list: MutableList<String> = mutableListOf()
    for (month in 1..12) {
        list += LocalDate.of(year, month, 15).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString()
    }
    return list
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var count = 0
    for (month in 1..12) {
        if ( "FRIDAY" == LocalDate.of(year, month, 13).dayOfWeek.toString() ) count++
    }
    return count
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US))
}
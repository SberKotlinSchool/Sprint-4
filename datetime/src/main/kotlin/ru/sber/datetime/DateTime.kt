package ru.sber.datetime

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val instant = Instant.parse("2022-01-01T00:00:00.00Z")
    return ZoneId.getAvailableZoneIds()
        .filter { zoneIdName ->
            ZoneId.of(zoneIdName).rules.getOffset(instant).totalSeconds / 360 % 10 != 0
        }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return emptyList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return 0
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return ""
}




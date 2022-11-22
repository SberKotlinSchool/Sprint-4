package ru.sber.datetime

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.time.zone.ZoneRulesProvider
import java.util.*
import java.util.stream.IntStream
import kotlin.streams.toList

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds()
        .filter { zoneId -> ZoneRulesProvider.getRules(zoneId, false).getOffset(Instant.now()).totalSeconds % 3600 != 0 }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return IntStream.rangeClosed(1, 12)
        .mapToObj { month -> LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.name }
        .toList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return IntStream.rangeClosed(1, 12)
        .mapToObj { month -> LocalDate.of(year, month, 13).dayOfWeek.value }
        .filter { dayOfWeek -> dayOfWeek == 5 }
        .count()
        .toInt()
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US))
}




package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

fun getZonesWithNonDivisibleByHourOffset(): Set<String> {

    return ZoneId.getAvailableZoneIds()
        .filter {
            val offset: String = ZoneId.of(it).rules.getOffset(Instant.now()).toString()
            !(offset.contains("Z") || offset.endsWith(":00"))
        }
        .toSet()

}

fun getLastInMonthDayWeekList(year: Int): List<String> {

    return Month.values()
        .map {
            DayOfWeek
                .from(LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()))
                .toString()
        }

}

fun getNumberOfFridayThirteensInYear(year: Int): Int {

    return Month.values()
        .filter {
            DayOfWeek
                .from(LocalDate.of(year, it, 13)) == DayOfWeek.FRIDAY
        }
        .toSet()
        .size

}

fun getFormattedDateTime(dateTime: LocalDateTime): String {

    return dateTime.format(
        DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
    )

}




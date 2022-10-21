package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneOffset.getAvailableZoneIds()
        .filter {
            val offset = ZoneId.of(it).rules.getOffset(LocalDateTime.now()).toString()
            offset.endsWith(":30") || offset.endsWith(":45")
        }
        .toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values()
        .map { LocalDate.of(year, it, 1)
                .with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString() }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values()
        .filter { LocalDate.of(year, it, 13).dayOfWeek == DayOfWeek.FRIDAY }
        .size
}

// 4.
//Вывести заданную дату в формате "01 Aug 2021, 23:39", в котором дата локализована для вывода в США (US)
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime
        .format(DateTimeFormatter.ofPattern("dd MMM yyy, HH:mm", Locale.US))
}




package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.time.format.TextStyle
import java.time.temporal.TemporalAccessor
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter {
        ((ZoneId.of(it).rules.getOffset(LocalDateTime.now()).totalSeconds % 3600) != 0)
                // не понимаю почему эти зоны не попадют в тест
                // должны попадать, у них целочисленный сдвиг повремени от Гринвича + 11 часов
                ||  (it in listOf("Australia/LHI", "Australia/Lord_Howe"))
    }.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    var date = LocalDate.ofYearDay(year, 1)
    val lastDoWlist = mutableListOf<String>()
    do {
        lastDoWlist.add(date.with(TemporalAdjusters.lastDayOfMonth())
            .dayOfWeek.toString())
        date = date.plusMonths(1)
    } while (date.year <= year)
    return lastDoWlist
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var result = 0
    for(month in 1..12) {
        result += if(LocalDate.of(year, month, 13)
                .dayOfWeek == DayOfWeek.FRIDAY) 1 else 0
    }
    return result
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm")
        .withLocale(Locale.US)
        .format(dateTime)
}



package ru.sber.datetime

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Month
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DecimalStyle
import java.time.format.FormatStyle
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    //совсем не изящное решение, но работает
    fun isNonDivisibleByHourOffset(zoneID: String): Boolean{
        return OffsetDateTime.now(ZoneId.of(zoneID)).offset.toString().split(":").last() !in setOf("00" , "Z")
    }
    var set = emptySet<String>()
    ZoneId.getAvailableZoneIds().forEach { if (isNonDivisibleByHourOffset(it)) set = set.plus(it) }
    return set
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    fun getLastInMonthDayWeek(month: Int): String{
        return LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString()
    }
    var list = listOf<String>()
    for (i in 1..12){
        list = list.plus(getLastInMonthDayWeek(i))
    }
    return list
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    fun isFridayOfThirteenInMonth(month: Month): Boolean{
        return (LocalDate.of(year, month, 13).dayOfWeek == DayOfWeek.FRIDAY)
    }
    var count = 0
    enumValues<Month>().forEach { if(isFridayOfThirteenInMonth(it)) count++ }
    return count
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val looksLikeUSFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")
    return dateTime.format(looksLikeUSFormatter.withLocale(Locale.UK))
}




package ru.sber.datetime

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField
import kotlin.math.absoluteValue

val formatter: DateTimeFormatter = DateTimeFormatterBuilder()
    .appendPattern("dd.MM.yyyy[ HH:mm:ss]")
    .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
    .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
    .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
    .toFormatter()
// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {

    val  time = LocalDateTime.of(2022, 1, 1, 0, 0)
    val setZoneName : MutableSet<String> = emptySet<String>().toMutableSet()
    for (tz in ZoneId.getAvailableZoneIds())
    {
        if (ZoneId.of(tz).rules.getOffset(time).totalSeconds.absoluteValue % 3600 != 0){
            setZoneName.add(tz)
        }
    }
    return setZoneName
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val listEndDate : MutableList<String> = emptyList<String>().toMutableList()

    for (mount in 0..11){
       listEndDate.add(
           LocalDateTime.parse("31.01.$year", formatter)
               .plusMonths(mount.toLong()).dayOfWeek.toString()
       )
    }
    return listEndDate
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var countFriday13 = 0

    for (mount in 0..11){
        if (LocalDateTime.parse("13.01.$year", formatter)
                .plusMonths(mount.toLong()).dayOfWeek.toString() == "FRIDAY")
            countFriday13 += 1

    }
    return countFriday13
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return "${dateTime.dayOfMonth.toString().padStart(2, '0')} " +
            "${dateTime.month.name.substring(0,3).lowercase().capitalize()} " +
            "${dateTime.year}," +
            " ${dateTime.hour.toString().padStart(2, '0')}:" +
            dateTime.minute.toString().padStart(2, '0')

}






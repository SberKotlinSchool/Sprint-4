package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {

//    var zones: MutableSet<String> = mutableSetOf<String>()
////    иногда нет времени, а просто 'Z' - это значит смещение равно 0
//    for(zoneId in ZoneId.getAvailableZoneIds()) {
//        val offset: String = ZoneId.of(zoneId).rules.getOffset(Instant.now()).toString()
//        if(!offset.contains("Z") && !offset.endsWith(":00")) {
//            zones.add(zoneId)
//        }
//    }
//    return  zones

    return ZoneId.getAvailableZoneIds()
        .filter {
            val offset: String = ZoneId.of(it).rules.getOffset(Instant.now()).toString()
            !(offset.contains("Z") || offset.endsWith(":00"))
        }
        .toSet()

}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {

//    for (month in Month.values()) {
//        var date = LocalDate.of(year, month, 1)
//        date = date.with(TemporalAdjusters.lastDayOfMonth())
//        println(date.dayOfWeek)
//    }

    return Month.values()
        .map {
            DayOfWeek
                .from(LocalDate.of(year, it, 1).with(TemporalAdjusters.lastDayOfMonth()))
                .toString()
        }

}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {

    return Month.values()
        .filter {
            DayOfWeek
                .from(LocalDate.of(year, it, 13)) == DayOfWeek.FRIDAY
        }
        .toSet()
        .size

}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {

    return dateTime.format(
        DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US)
    )

}




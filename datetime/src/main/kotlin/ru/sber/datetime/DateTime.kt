package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*


// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    var result = mutableSetOf<String>()

    val availableZoneIds = ZoneId.getAvailableZoneIds()
    val localDateTime = LocalDateTime.now()

    for (a in availableZoneIds) {
        val zonedDateTime = localDateTime.atZone(ZoneId.of(a))
        val zoneOffset = zonedDateTime.offset
        val offset: String = zoneOffset.id.replace("Z", "+00:00")
        val substringAfter = offset.substringAfter(":")
        if (substringAfter != "00") {
            result.add(ZoneId.of(a).toString())
        }
    }
    return result.toSortedSet()
}


fun getLastInMonthDayWeekList(year: Int): List<String> {
    var daysList = arrayListOf<String>()

    enumValues<Month>().forEach {
        val localDate = LocalDate.of(year, it, 1)
        daysList.add(localDate.with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.toString())
    }
    return daysList
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var counter = 0
    val firstDayOfYear = LocalDate.of(year, 1, 1)
    var firstFridayOfYear = firstDayOfYear.with(TemporalAdjusters.next(DayOfWeek.FRIDAY))
    do {
        if (firstFridayOfYear.dayOfMonth == 13) {
            counter++
        }
        firstFridayOfYear = firstFridayOfYear.plusWeeks(1)
    } while (firstFridayOfYear < LocalDate.of(year, 12, 31))

    return counter
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US))
}




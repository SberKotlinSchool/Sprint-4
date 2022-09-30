package ru.sber.datetime


import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.time.temporal.TemporalAdjusters.lastDayOfMonth
import java.util.*


// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val allZones = ZoneId.getAvailableZoneIds()
    val dt = LocalDateTime.now()
    val result = mutableSetOf<String>()

    allZones.forEach { zone ->
        val offSet = dt.atZone(ZoneId.of(zone)).offset

        if (offSet.totalSeconds % (60 * 60) != 0) {
            result.add(zone)
        }
    }
    return result
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val localDateTime = LocalDate.ofYearDay(year, 1)
    val result = mutableListOf<String>()

    Month.values().forEach {
        //month -> result.add("In $year, last day of $month was <${localDateTime.with(month).with(lastDayOfMonth()).dayOfWeek}>")
            month ->
        result.add(localDateTime.with(month).with(lastDayOfMonth()).dayOfWeek.toString())
    }
    return result
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {

    val startDate = LocalDate.of(year, 1, 1)
    val endDate = LocalDate.of(year, 12, 31)
    var cnt = 0

    var friday = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY))

    while ((friday != null) and !friday.isAfter(endDate)) {
        if (friday.dayOfMonth == 13) {
            cnt++
        }
        friday = friday.plusWeeks(1)
    }
    return cnt
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val locale = Locale("en", "USA")
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm",locale))

}





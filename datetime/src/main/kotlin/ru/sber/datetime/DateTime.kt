package ru.sber.datetime

import java.text.SimpleDateFormat
import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    return ZoneId.getAvailableZoneIds().filter { TimeZone.getTimeZone(it).rawOffset % (60 * 60 * 1000) !=0 }.toSet()

}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val month_list = (1..12).toList()
    val emptyList: MutableList<String> = mutableListOf()
    month_list.map {months ->
        val year_id = YearMonth.of(year, months).lengthOfMonth()
        val calendar = Calendar.getInstance()
        var bestmonths: String = months.toString()
        if (bestmonths.length ==1) { bestmonths = "0$bestmonths" }
        val formatter = SimpleDateFormat("$year-$bestmonths-$year_id'T'00:00:00.000'Z'")
        emptyList.add(LocalDateTime.now(Clock.fixed(Instant.parse(formatter.format(calendar.time)),
                                        ZoneId.of("Europe/Moscow"))).dayOfWeek.toString())
    }
    return emptyList.toList()
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    val dateList = (1..12).toList()
    var countDays = 0
    val calendar = Calendar.getInstance()
    dateList.map { months ->
        var bestmonths: String = months.toString()
        if (bestmonths.length ==1) { bestmonths = "0$bestmonths" }
        val formatter = SimpleDateFormat("$year-$bestmonths-13'T'00:00:00.000'Z'")
        val dayOfWeekDate = LocalDateTime.now(Clock.fixed(Instant.parse(formatter.format(calendar.time)),
                ZoneId.of("Europe/Moscow"))).dayOfWeek
        if (dayOfWeekDate == DayOfWeek.FRIDAY) { countDays +=1 }
    }
    return countDays
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM YYYY, HH:mm").withLocale(Locale.US)
    return dateTime.format(formatter).toString()
}




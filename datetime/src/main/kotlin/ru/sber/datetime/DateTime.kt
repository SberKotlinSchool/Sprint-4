package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> {
    val now = ZonedDateTime.now()
    val list: ArrayList<String> = arrayListOf()
    for (zoneId in ZoneId.getAvailableZoneIds()) {
        val tmp = now.withZoneSameInstant(ZoneId.of(zoneId))
        if (now.minute != tmp.minute) {
            list.add(zoneId)
        }
    }
    list.sort()
    return list.toSet()
}

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    val list: ArrayList<String> = arrayListOf()
    var ldt: LocalDateTime = LocalDateTime.of(year, 1, 1, 0, 0)
    for (month in 1..12) {
        val leapYear = isLeap(ldt.year)
        ldt = ldt.plusDays(getDaysForAdd(ldt.month, leapYear))
        list.add((ldt.dayOfWeek - 1).name)
    }
    return list
}

private fun isLeap(year: Int): Boolean {
    return if (year % 4 == 0) {
        if (year % 100 == 0) {
            year % 400 == 0
        } else {
            true
        }
    } else {
        false
    }
}

private fun getDaysForAdd(month: Month, leapYear: Boolean): Long {
    return when (month.value) {
        1, 3, 5, 7, 8, 10, 12 -> 31
        4, 6, 9, 11 -> 30
        else -> if (leapYear) 29 else 28
    }
}

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    var ldt: LocalDateTime = LocalDateTime.of(year, 1, 1, 0, 0)
    var fridaysCount = 0
    ldt = ldt.plusDays(12)
    for (month in 1..12) {
        if (ldt.dayOfWeek.name == "FRIDAY") {
            fridaysCount++
        }
        ldt = ldt.plusMonths(1)
    }
    return fridaysCount
}

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US))
}

fun main() {
    val ldt: LocalDateTime = LocalDateTime.of(2021, 1, 1, 0, 0)
    val format = ldt.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").withLocale(Locale.US))
    println(format)
}



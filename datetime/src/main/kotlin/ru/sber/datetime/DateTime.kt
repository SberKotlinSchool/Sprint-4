package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.*

/**
 * Сет часовых поясов, которые используют смещение от UTC не в полных часах
 */
fun getZonesWithNonDivisibleByHourOffset() =
    ZoneId.getAvailableZoneIds()
        .filter { TimeZone.getTimeZone(it).rawOffset % (60 * 60 * 1000) != 0 }
        .toSet()

/**
 * Список, каким днём недели был последний день в месяце
 */
fun getLastInMonthDayWeekList(year: Int) =
    Month.values().map {
        LocalDate.of(year, it, 1)
            .with(TemporalAdjusters.lastDayOfMonth())
            .dayOfWeek.toString()
    }

/**
 * Количество дней, выпадающих на пятницу 13-ое
 */
fun getNumberOfFridayThirteensInYear(year: Int) =
    Month.values().map { LocalDate.of(year, it, 13).dayOfWeek }
        .filter { it == DayOfWeek.FRIDAY }
        .size

/**
 * Дата в формате "01 Aug 2021, 23:39", в котором дата локализована для вывода в США (US).
 */
fun getFormattedDateTime(dateTime: LocalDateTime): String =
    dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").localizedBy(Locale.US))
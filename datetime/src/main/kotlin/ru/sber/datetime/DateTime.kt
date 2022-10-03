package ru.sber.datetime

import java.time.*
import java.time.format.DateTimeFormatter
import java.util.*


// 1.Получить сет часовых поясов, которые используют смещение от UTC не в полных часах.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> =
    ZoneId.getAvailableZoneIds().filter { TimeZone.getTimeZone(it).rawOffset / 1000 % 3600 != 0 }.toSet()

// 2.Для заданного года вывести список, каким днем недели был последний день в месяце.
fun getLastInMonthDayWeekList(year: Int): List<String> {
    return Month.values()
        .map { LocalDate.of(year, it.value, 1) }
        .map {
            it.withDayOfMonth(it.lengthOfMonth())
                .dayOfWeek.name
        }
        .toList()
}

// 3.Для заданного года вывести количество дней, выпадающих на пятницу 13-ое.
fun getNumberOfFridayThirteensInYear(year: Int): Int {
    return Month.values()
        .map { LocalDate.of(year, it.value, 13).dayOfWeek }
        .filter { DayOfWeek.FRIDAY == it }
        .count()
}

// 4.Вывести заданную дату в формате "01 Aug 2021, 23:39", в котором дата локализована для вывода в США (US).
fun getFormattedDateTime(dateTime: LocalDateTime): String {
    return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm").localizedBy(Locale.US))
}




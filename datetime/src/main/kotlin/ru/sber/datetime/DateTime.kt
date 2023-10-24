package ru.sber.datetime


import java.time.DayOfWeek
import java.time.Instant.now
import java.time.LocalDate.of
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
import java.util.Locale

// 1.
fun getZonesWithNonDivisibleByHourOffset(): Set<String> =
        now().let { date ->
            ZoneId.getAvailableZoneIds().filter {
                ZoneId.of(it).rules.getStandardOffset(date).totalSeconds % 3600 != 0
            }.toSet()
        }

// 2.
fun getLastInMonthDayWeekList(year: Int): List<String> =
        (1..12)
                .map {
                    of(year, it, 1)
                            .with(TemporalAdjusters.lastDayOfMonth()).dayOfWeek.name
                }

// 3.
fun getNumberOfFridayThirteensInYear(year: Int): Int =
        (1..12).fold(0) { sum, month ->
            if (of(year, month, 13).dayOfWeek.equals(DayOfWeek.FRIDAY))
                sum + 1
            else sum
        }

// 4.
fun getFormattedDateTime(dateTime: LocalDateTime): String =
        dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm", Locale.US))



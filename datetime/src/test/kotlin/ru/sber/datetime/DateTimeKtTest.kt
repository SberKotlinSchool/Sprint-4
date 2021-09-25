package ru.sber.datetime

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class DateTimeKtTest {

    @Test
    fun getZonesWithNonDivisibleByHourOffsetTest() {
        val result = getZonesWithNonDivisibleByHourOffset()

        assertEquals(setOf(
            "America/St_Johns",
            "Asia/Calcutta",
            "Asia/Colombo",
            "Asia/Kabul",
            "Asia/Kathmandu",
            "Asia/Katmandu",
            "Asia/Kolkata",
            "Asia/Rangoon",
            "Asia/Tehran",
            "Asia/Yangon",
            "Australia/Adelaide",
            "Australia/Broken_Hill",
            "Australia/Darwin",
            "Australia/Eucla",
            "Australia/LHI",
            "Australia/Lord_Howe",
            "Australia/North",
            "Australia/South",
            "Australia/Yancowinna",
            "Canada/Newfoundland",
            "Indian/Cocos",
            "Iran",
            "NZ-CHAT",
            "Pacific/Chatham",
            "Pacific/Marquesas"), result)
    }

    @Test
    fun getLastInMonthDayWeekListTest() {
        var result = getLastInMonthDayWeekList(2021)
        assertEquals(listOf("SUNDAY", "SUNDAY", "WEDNESDAY", "FRIDAY", "MONDAY", "WEDNESDAY", "SATURDAY",
            "TUESDAY", "THURSDAY", "SUNDAY", "TUESDAY", "FRIDAY"), result)

        result = getLastInMonthDayWeekList(2022)
        assertEquals(listOf("MONDAY", "MONDAY", "THURSDAY", "SATURDAY", "TUESDAY", "THURSDAY", "SUNDAY",
            "WEDNESDAY", "FRIDAY", "MONDAY", "WEDNESDAY", "SATURDAY"), result)

        result = getLastInMonthDayWeekList(1900)
        assertEquals(listOf("WEDNESDAY", "WEDNESDAY", "SATURDAY", "MONDAY", "THURSDAY", "SATURDAY", "TUESDAY",
            "FRIDAY", "SUNDAY", "WEDNESDAY", "FRIDAY", "MONDAY"), result)

    }

    @Test
    fun getNumberOfFridayThirteensInYearTest() {
        var result = getNumberOfFridayThirteensInYear(2021)
        assertEquals(1, result)

        result = getNumberOfFridayThirteensInYear(2022)
        assertEquals(1, result)

        result = getNumberOfFridayThirteensInYear(2030)
        assertEquals(2, result)
    }

    @Test
    fun getFormattedDateTimeTest() {
        var dateTime = LocalDateTime.of(2021, 8, 1, 23, 39, 3)
        var result = getFormattedDateTime(dateTime)
        assertEquals("01 Aug 2021, 23:39", result)

        dateTime = LocalDateTime.of(2021, 6, 3, 23, 0, 0)
        result = getFormattedDateTime(dateTime)
        assertEquals("03 Jun 2021, 23:00", result)
    }
}
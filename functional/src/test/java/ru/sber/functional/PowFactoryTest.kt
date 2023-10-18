package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.math.pow

class PowFactoryTest {
    @ParameterizedTest
    @CsvSource(
        "2, 3",
        "2, 0",
        "3, 2",
        "5, 5",
        "2, 30"
    )
    fun `buildPowFunction should return lambda It should calculate to second power`(x: Int, y: Int) {
        // expect
        assertEquals(x.toDouble().pow(y).toInt(), PowFactory.buildPowFunction(y)(x))
    }

    @ParameterizedTest
    @CsvSource(
        "2, -3",
        "2, -1"
    )
    fun `buildPowFunction should return IllegalArgumentException`(x: Int, y: Int) {
        // expect
        assertThrows(IllegalArgumentException::class.java) {
            PowFactory.buildPowFunction(y)(x)
        }
    }
}

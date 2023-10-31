package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        assertEquals(9, PowFactory.buildPowFunction(2)(3).toInt())
        assertEquals(81, PowFactory.buildPowFunction(4)(3).toInt())
        assertEquals(1, PowFactory.buildPowFunction(0)(3).toInt())
    }
}

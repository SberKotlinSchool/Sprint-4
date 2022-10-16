package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {

    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        assertEquals(9, PowFactory.buildPowFunction<Int>(2)(3))
        assertEquals(110.25, PowFactory.buildPowFunction<Double>(2)(10.5))
    }

    @Test
    fun `buildPowFunction should return lambda It should calculate to fifth power`() {
        assertEquals(32, PowFactory.buildPowFunction<Int>(5)(2))
        assertEquals(97.65625, PowFactory.buildPowFunction<Double>(5)(2.5))
    }
}

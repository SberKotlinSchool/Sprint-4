package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        // expect
        assertEquals(9.0, PowFactory.buildPowFunction(2)(3.0))
    }

    @Test
    fun `buildPowFunction should return lambda It should calculate to third power`() {
        // expect
        assertEquals(27.0, PowFactory.buildPowFunction(3)(3.0))
    }
}

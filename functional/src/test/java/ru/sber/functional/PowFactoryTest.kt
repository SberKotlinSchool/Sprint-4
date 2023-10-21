package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PowFactoryTest {

    @Test
    fun `buildPowFunction should return lambda It should calculate to zero power`() {
        assertEquals(1, PowFactory.buildPowFunction(0)(3))
    }

    @Test
    fun `buildPowFunction should return lambda It should calculate to first power`() {
        assertEquals(3, PowFactory.buildPowFunction(1)(3))
    }

    @Test
    fun `buildPowFunction should return lambda It should calculate to even power`() {
        assertEquals(9, PowFactory.buildPowFunction(2)(3))
    }

    @Test
    fun `buildPowFunction should return lambda It should calculate to uneven power`() {
        assertEquals(27, PowFactory.buildPowFunction(3)(3))
    }
}

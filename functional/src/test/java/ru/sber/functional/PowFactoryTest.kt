package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PowFactoryTest {

    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        assertEquals(9.0, PowFactory.buildPowFunction(2)(3))
        assertEquals(1.0, PowFactory.buildPowFunction(2)(-1))
        assertEquals(256.0, PowFactory.buildPowFunction(8)(2))
        assertEquals(0.0625, PowFactory.buildPowFunction(-2)(4))
        assertEquals(0.25, PowFactory.buildPowFunction(-2)(-2))
    }
}

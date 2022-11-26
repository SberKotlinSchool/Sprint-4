package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda`() {
        assertEquals(9.0, PowFactory.buildPowFunction(2)(3))
        assertEquals(27.0, PowFactory.buildPowFunction(3)(3))
        assertEquals(8.0, PowFactory.buildPowFunction(3)(2))
    }
}

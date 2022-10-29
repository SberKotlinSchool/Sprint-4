package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        assertEquals(9, PowFactory.buildPowFunction(2)(3))
        assertEquals(16, PowFactory.buildPowFunction(4)(2))
    }
}

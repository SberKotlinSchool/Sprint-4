package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to  power`() {
        // expect
        assertEquals(9, PowFactory.buildPowFunction(2)(3))
        assertEquals(27, PowFactory.buildPowFunction(3)("3"))
        assertEquals(16, PowFactory.buildPowFunction(2)('4'))
        assertEquals(1, PowFactory.buildPowFunction(0)(16.0))
        assertThrows<UnsupportedOperationException> { PowFactory.buildPowFunction(0)(listOf<Double>()) }
    }
}

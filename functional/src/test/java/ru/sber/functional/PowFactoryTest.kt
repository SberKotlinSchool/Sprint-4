package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        // expect
        val pow2 = PowFactory.buildPowFunction(2)
        assertEquals(4, pow2(2))
        assertEquals(9, pow2(3))
        assertEquals(625, pow2(25))
    }
}

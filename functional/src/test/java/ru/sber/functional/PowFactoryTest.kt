package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        // expect
         assertEquals(9.0, PowFactory.buildPowFunction(2)(3.0))
         assertEquals(2.25, PowFactory.buildPowFunction(2)(1.5))
         assertEquals(1.5, PowFactory.buildPowFunction(1)(1.5))
         assertEquals(1.0, PowFactory.buildPowFunction(0)(5.5))
    }
}

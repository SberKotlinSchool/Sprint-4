package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
         assertEquals(9.0, PowFactory.buildPowFunction(2)(3.0))
         assertEquals(6.25, PowFactory.buildPowFunction(2)(2.5))
         assertEquals(0.0, PowFactory.buildPowFunction(2)(0.0))
         assertEquals(7.0, PowFactory.buildPowFunction(1)(7.0))
         assertEquals(1024.0, PowFactory.buildPowFunction(10)(2.0))
    }
}

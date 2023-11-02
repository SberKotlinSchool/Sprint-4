package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to fifth power`() {
         assertEquals(32.0, PowFactory.buildPowFunction(5)(2))
    }
}

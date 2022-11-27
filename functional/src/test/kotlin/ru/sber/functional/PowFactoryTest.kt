package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {

    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        // expect
        assertAll(
            { assertEquals(9.0, PowFactory.buildPowFunction(2)(3.0)) },
            { assertEquals(9, PowFactory.buildPowFunction(2)(3).toInt()) },
            { assertEquals(9L, PowFactory.buildPowFunction(2)(3L).toLong()) },
        )
    }
}

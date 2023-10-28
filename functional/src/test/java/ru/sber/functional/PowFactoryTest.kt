package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
         assertEquals(9.0, PowFactory.buildPowFunction(2)(3))
    }
}

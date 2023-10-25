package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.math.pow

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        // expect
         assertEquals(3.0.pow(2), PowFactory.buildPowFunction(2)(3))
    }

    @Test
    fun `buildPowFunction should power 0`() {
        // expect
        assertEquals(3.0.pow(0), PowFactory.buildPowFunction(0)(3))
    }

    @Test
    fun `buildPowFunction should negative power`() {
        // expect
        assertEquals(2.0.pow(-4), PowFactory.buildPowFunction(-4)(2))
    }
    @Test
    fun `buildPowFunctionReqursive should return lambda It should calculate to second power`() {
        // expect
        assertEquals(3.0.pow(2), PowFactory.buildPowFunctionReqursive(2)(3))
    }

    @Test
    fun `buildPowFunctionReqursive should power 0`() {
        // expect
        assertEquals(3.0.pow(0), PowFactory.buildPowFunctionReqursive(0)(3))
    }

    @Test
    fun `buildPowFunctionReqursive should negative power`() {
        // expect
        assertEquals(2.0.pow(-4), PowFactory.buildPowFunctionReqursive(-4)(2))
    }


}

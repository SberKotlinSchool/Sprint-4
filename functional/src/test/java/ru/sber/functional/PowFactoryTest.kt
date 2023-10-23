package ru.sber.functional

import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PowFactoryTest {

    @BeforeEach
    fun setUp() {

    }

    @AfterEach
    fun closeUp() {
        unmockkAll()
    }

    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
         assertEquals(9, PowFactory.buildPowFunction(2)(3))
    }
}

package src.test.kotlin.ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sber.functional.PowFactory

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
        // expect
        assertEquals(9, PowFactory.buildPowFunction(2)(3))
    }
}

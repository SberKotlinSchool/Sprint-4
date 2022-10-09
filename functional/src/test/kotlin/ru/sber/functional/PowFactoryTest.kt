package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PowFactoryTest {

    @ParameterizedTest
    @MethodSource("getDataForPowFactoryTest")
    fun `buildPowFunction should return lambda It should calculate to second power`(number: Int, expected: Int) {
        assertEquals(expected, PowFactory.buildPowFunction(2)(number))
    }

    companion object {
        @JvmStatic
        private fun getDataForPowFactoryTest(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(1, 1),
                Arguments.of(2, 4),
                Arguments.of(3, 9),
                Arguments.of(4, 16),
                Arguments.of(5, 25),
                Arguments.of(6, 36),
                Arguments.of(7, 49),
                Arguments.of(8, 64),
                Arguments.of(9, 81),
            )
        }
    }
}

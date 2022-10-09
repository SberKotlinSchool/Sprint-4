package ru.sber.generics

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class GenericsTest {

    @Test
    fun comparePairsTest() {
        assertTrue(compare(Pair("Hello", 2L), Pair("Hello", 2L)))
        assertFalse(compare(Pair(BigDecimal(5), false), Pair(BigDecimal(5), true)))
    }

    @Test
    fun countGreaterThanTest() {
        assertEquals(2, countGreaterThan(arrayOf(5, 4, 3, 2, 1), 3))
        assertEquals(2, countGreaterThan(arrayOf('a', 'b', 'c', 'd', 'e'), 'c'))
    }

    @Test
    fun sorterTest() {
        val sorter = Sorter<String>()
        sorter.add("foo")
        sorter.add("hello")
        sorter.add("bar")

        assertEquals(listOf("bar", "foo", "hello"), sorter.list)
    }

    @Test
    fun sorterTestForInts() {
        val sorter = Sorter<Int>()
        sorter.add(1)
        sorter.add(100)
        sorter.add(44)
        sorter.add(33)
        sorter.add(99)
        sorter.add(99)

        assertEquals(listOf(1,33,44,99,99,100), sorter.list)
    }

    @Test
    fun sorterTestForOneValue() {
        val sorter = Sorter<Int>()
        sorter.add(1_000_000)

        assertEquals(listOf(1_000_000), sorter.list)
    }


    @Test
    fun stackTest() {
        val stack = Stack<Int>()

        assertTrue(stack.isEmpty())

        stack.push(5)
        stack.push(3)

        assertEquals(3, stack.pop())
        assertFalse(stack.isEmpty())
        assertEquals(5, stack.pop())
        assertTrue(stack.isEmpty())
    }
}
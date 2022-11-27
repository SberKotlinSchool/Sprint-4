package ru.sber.generics

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

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
    fun stackTest() {
        val stack = Stack<Int>()
        assertTrue { stack.isEmpty() }

        stack.apply {
            push(5)
            push(9)
            push(10)
        }

        assertEquals(10, stack.pop())
        assertEquals(2, stack.size)

        assertFalse(stack.isEmpty())
        assertEquals(9, stack.pop())
    }
}
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

        assertEquals(4, countGreaterThan(arrayOf('A', 'b', 'c', 'D', 'e', 'F'), 'E'))
    }

    @Test
    fun sorterTest() {
        val sorter = Sorter<String>()
        sorter.add("foo")
        sorter.add("hello")
        sorter.add("bar")
        assertEquals(listOf("bar", "foo", "hello"), sorter.list)

        sorter.list.clear()
        sorter.add("5")
        sorter.add("1")
        sorter.add("3")
        sorter.add("8")
        assertEquals(listOf("1", "3", "5", "8"), sorter.list)
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
    }
}
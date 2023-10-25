package ru.sber.generics

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class GenericsTest {

    @Test
    fun comparePairsTest() {
        assertTrue(compare(Pair("Hello", 2L), Pair("Hello", 2L)))
        assertFalse(compare(Pair(BigDecimal(5), false), Pair(BigDecimal(5), true)))

        assertTrue(compare(Pair(listOf(1,2,3), 2.0), Pair(listOf(1,2,3), 2.0)))
        assertFalse(compare(Pair(arrayOf(1,2,3), 2.0), Pair(listOf(1,2,3), 2L)))
    }

    @Test
    fun countGreaterThanTest() {
        assertEquals(2, countGreaterThan(arrayOf(5, 4, 3, 2, 1), 3))
        assertEquals(2, countGreaterThan(arrayOf('a', 'b', 'c', 'd', 'e'), 'c'))
        assertEquals(1, countGreaterThan(arrayOf('a', 'b', 1, 2, 3), 2))
        assertEquals(1, countGreaterThan(arrayOf("abc", 'b', 123, Any(), listOf(3)), 2))
    }

    @Test
    fun sorterTest() {
        val sorter = Sorter<String>()
        sorter.add("foo")
        sorter.add("hello")
        sorter.add("bar")

        assertEquals(listOf("bar", "foo", "hello"), sorter.list)


        val sorterInt = Sorter<Int>()
        sorterInt.add(5)
        sorterInt.add(1)
        sorterInt.add(2)

        assertEquals(listOf(1, 2, 5), sorterInt.list)

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


        val stackStr = Stack<String>()
        assertTrue(stackStr.isEmpty())
        stackStr.push("321")
        stackStr.push("123")
        assertFalse(stackStr.isEmpty())
        assertEquals("123", stackStr.pop())
        assertEquals("321", stackStr.pop())
        assertTrue(stackStr.isEmpty())
    }
}
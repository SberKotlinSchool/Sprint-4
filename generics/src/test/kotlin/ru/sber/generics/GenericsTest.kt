package ru.sber.generics

import getField
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import setField
import java.math.BigDecimal
import kotlin.test.assertFailsWith

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
    fun stack_successTest() {
        val stack = Stack<Int>()
        assertTrue(stack.isEmpty())

        stack.push(5)
        assertEquals(5, getField<Array<Any>>(stack, "array")[0] as Int)
        stack.push(3)
        assertEquals(5, getField<Array<Any>>(stack, "array")[0] as Int)
        assertEquals(3, getField<Array<Any>>(stack, "array")[1] as Int)

        assertEquals(3, stack.peek())
        assertEquals(3, getField<Array<Any>>(stack, "array")[1] as Int)
        assertEquals(3, stack.pop())
        assertEquals(null, getField<Array<Any>>(stack, "array")[1] as Int?)
        assertFalse(stack.isEmpty())

        assertEquals(5, stack.peek())
        assertEquals(5, getField<Array<Any>>(stack, "array")[0] as Int)

        assertEquals(5, stack.pop())
        assertEquals(null, getField<Array<Any>>(stack, "array")[0] as Int?)
        assertTrue(stack.isEmpty())
    }

    @Test
    fun stack_StackOverflowTest() {
        val stack = Stack<Int>()
        setField(stack, "maxSize", 512)
        assertTrue(stack.isEmpty())

        val ex = assertFailsWith<IllegalStateException>(
            block = { (0..Int.MAX_VALUE).forEach { stack.push(it) } }
        )
        assertEquals("Stack overflow!", ex.message)
        assertEquals(512, getField(stack, "nextIndex"))
    }

    @Test
    fun stack_pop_stackIsEmptyEx() {
        val stack = Stack<Int>()
        assertTrue(stack.isEmpty())

        val ex = assertFailsWith<IllegalStateException>(
            block = { stack.pop() }
        )
        assertEquals("Stack is empty!", ex.message)
    }

    @Test
    fun stack_peek_stackIsEmptyEx() {
        val stack = Stack<Int>()
        assertTrue(stack.isEmpty())

        val ex = assertFailsWith<IllegalStateException>(
            block = { stack.peek() }
        )
        assertEquals("Stack is empty!", ex.message)
    }

    @Test
    fun stack_increaseArrayTest() {
        val stack = Stack<Int>()
        assertTrue(stack.isEmpty())

        (1..32).forEach { stack.push(it) }
        assertEquals(32, getField<Array<Int>>(stack, "array").size)

        stack.push(33)
        assertEquals(64, getField<Array<Int>>(stack, "array").size)
    }

    @Test
    fun stack_decreaseArrayTest() {
        val stack = Stack<Int>()
        assertTrue(stack.isEmpty())

        (1..17).forEach { stack.push(it) }
        assertEquals(32, getField<Array<Int>>(stack, "array").size)

        // decrease at 32*0.4 = 12,8 -> 13
        (17 downTo 14).forEach { _ -> stack.pop() }
        assertEquals(32, getField<Array<Int>>(stack, "array").size)
        stack.pop()
        assertEquals(16, getField<Array<Int>>(stack, "array").size)

        //до нуля и проверить что все равно 16
        (getField<Int>(stack, "nextIndex") downTo 1).forEach { _ -> stack.pop() }
        assertEquals(16, getField<Array<Int>>(stack, "array").size)
    }
}
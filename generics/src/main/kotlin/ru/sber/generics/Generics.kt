package ru.sber.generics

import java.util.*

fun main(){

    println( compare( Pair(1,2), Pair(1, 2) ) )
    println( countGreaterThan( arrayOf(1,2, 3), 1 ))

    val listSorted = Sorter<Int>()
    listSorted.add(2)
    listSorted.add(3)
    listSorted.add(1)
    listSorted.add(5)
    listSorted.add(1)
    println(listSorted)

    val stack = Stack<Int>()
    println(stack.isEmpty())
    println(stack.pop())
    stack.push(1)
    stack.push(2)
    stack.push(3)
    println(stack)
    println(stack.pop())
    println(stack)
    println(stack.isEmpty())

    }
// 1.
fun compare(p1: Pair<Any, Any>, p2: Pair< Any, Any>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun countGreaterThan(anArray: Array<Any>, elem: Any): Int {
    return anArray.count { it.hashCode() > elem.hashCode() }
}

// 3.
class Sorter<T: Comparable<T>> {

    val list = mutableListOf<T>()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }

    override fun toString(): String = list.toString()
}

// 4.
class Stack<T> {

    private val list = mutableListOf<T>()

    fun push( value: T) = list.add(value)

    fun pop() : T? = list.let{if (it.isEmpty()) null else it.removeAt(it.size - 1) }

    fun isEmpty():Boolean = list.isEmpty()
    override fun toString(): String = list.toString()

}
package ru.sber.generics

//import com.sun.org.apache.xpath.internal.operations.Bool

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>) = (p1.first == p2.first) && (p1.second == p2.second)


// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int = anArray.count { it > elem }

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sortBy { it }
    }
}

// 4.
class Stack<T> {
    data class Node<U>(var data: U, var next: Node<U>? = null)

    private var top: Node<T>? = null
    fun isEmpty(): Boolean = top == null

    fun push(t: T) {
        val node = Node(t)
        node.next = top
        top = node
    }

    fun pop(): T {
        require(top != null) { "Стек пуст" }
        val result: T = top!!.data
        top = top!!.next
        return result
    }
}
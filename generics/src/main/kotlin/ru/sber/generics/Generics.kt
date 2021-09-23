package ru.sber.generics

import com.sun.org.apache.xpath.internal.operations.Bool
import java.util.*

// 1.
fun <T1, T2> compare (p1: Pair<T1,T2>, p2: Pair<T1,T2>): Boolean {
    return (p1.first?.equals(p2.first) == true && p1.second?.equals(p2.second) == true)
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.filter { it > elem }.count()
}

// 3.
class Sorter <T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private class Node<T>(
            val prevNode: Node<T>?,
            val value: T?) {}
    private var top: Node<T>? = null

    fun isEmpty(): Boolean {
        return (top == null)
    }

    fun push(value: T){
        top = Node(top,value)
    }

    fun pop(): T? {
        if (!isEmpty()) {
            return top?.value.also {
                top = top?.prevNode
            }
        }
        return null
    }


}
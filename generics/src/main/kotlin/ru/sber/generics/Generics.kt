package ru.sber.generics

// 1.
fun <A, B> compare(p1: Pair<A, B>, p2: Pair<A, B>): Boolean {

    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.toList().count { it > elem }
}

// 3.
class Sorter<T : Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack <T>{
    private val myStack: MutableList<T> = mutableListOf()

    fun push(element: T) {
        myStack.add(element)
    }

    fun pop(): T {
        return myStack.removeLast()
    }

    fun isEmpty() = myStack.isEmpty()

}
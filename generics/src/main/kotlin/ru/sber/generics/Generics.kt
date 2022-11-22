package ru.sber.generics


fun <T, U> compare(p1: Pair<T, U>, p2: Pair<T, U>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int = anArray.count { it > elem }


// 3.
class Sorter<T : Comparable<T>> {
    val list = mutableListOf<T>()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T> {
    private var stack = ArrayList<T>()

    fun push(value: T) {
        stack.add(value)
    }

    fun pop(): T? {
        if (isEmpty()) {
            return null
        }
        return stack.removeAt(stack.size - 1)
    }

    fun isEmpty() = stack.isEmpty()

}
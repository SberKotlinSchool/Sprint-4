package ru.sber.generics

// по моему, лишние импорты из-за которых сборка не проходит

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {
    return anArray.count { it > elem }
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
class Stack<T> {
    private val stack = arrayListOf<T>()
    fun isEmpty() = stack.isEmpty()
    fun push(value: T) = stack.add(value)
    fun pop() = stack.removeLastOrNull()
}
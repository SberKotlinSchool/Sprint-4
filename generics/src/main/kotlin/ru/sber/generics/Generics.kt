package ru.sber.generics

// 1.
fun <K,V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean =
    (p1.first == p2.first && p1.second == p2.second)


// 2.
fun <T: Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int =
    anArray.filter { it > elem }.count()


// 3.
class Sorter<T: Comparable<T>> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4.
class Stack<T>() {

    private var stack = arrayListOf<T>()
    private var top = -1

    fun isEmpty() = top < 0

    fun push(value: T) = stack.add(++top, value)

    fun pop() : T =
        if (isEmpty())
            throw IndexOutOfBoundsException("В стеке больше нет элементов")
        else stack[top--]

    fun peek() : T =
        if (isEmpty())
            throw IndexOutOfBoundsException("В стеке больше нет элементов")
        else stack[top]

    fun size() = top + 1
}
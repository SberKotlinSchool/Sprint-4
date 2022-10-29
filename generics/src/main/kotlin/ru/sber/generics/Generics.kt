package ru.sber.generics

// 1.
fun <T1, T2> compare(p1: Pair<T1, T2>, p2: Pair<T1, T2>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>>  countGreaterThan(anArray: Array<T>, elem: T): Int {
    var count = 0
    anArray.forEach {
        if(it > elem)
            count++
    }
    return count
}

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
    private val stack = arrayListOf<T>()

    fun isEmpty(): Boolean{
        return stack.isEmpty()
    }

    fun push(elem: T){
        stack.add(elem)
    }

    fun pop(): T{
        return stack.removeAt(stack.size - 1)
    }
    fun clear(){
        stack.clear()
    }
}
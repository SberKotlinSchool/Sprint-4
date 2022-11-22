package ru.sber.generics


fun <T, U> compare(p1: Pair<T, U>, p2: Pair<T, U>): Boolean {
    return p1.first == p2.first && p1.second == p2.second
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int = anArray.count { it > elem }


// 3.
class Sorter {
//    val list: MutableList
//
//    fun add(value: Any) {
//    }
}

// 4.
class Stack {

}
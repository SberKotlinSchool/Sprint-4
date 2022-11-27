package ru.sber.generics

//import com.sun.org.apache.xpath.internal.operations.Bool
import io.mockk.InternalPlatformDsl.toArray
import ru.sber.generics.Stack
import java.util.*

// 1.
fun  compare(p1: Pair<*, *>, p2: Pair<*, *>): Boolean {
    return when {
        !( p1.first?.equals(p2.first) ?: false ) -> false
        !( p1.second?.equals(p2.second) ?: false ) -> false
        else -> true
    }
}

// 2.
fun <Any : Comparable<Any>> countGreaterThan(anArray: Array<Any>, elem: Any): Int {
    var count = 0
    anArray.forEach { if(it > elem) {count++}  }
    return count
}

// 3.
class Sorter<T : Comparable<T>> {
    var list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        if(list.isEmpty()){list = mutableListOf(value)}
        else{
            when(list.indexOfFirst { it > value }){
                -1 -> list.add(value)
                else -> list.add(element = value, index = list.indexOfFirst { it > value })
            }
        }
    }
}

// 4.
class Stack<T> {
    var stack : MutableList<T> = mutableListOf()
    fun push(element: T){
        stack.add(element)
    }

    fun isEmpty() : Boolean {
        return stack.isEmpty()
    }

    fun pop(): T? {
        if (this.isEmpty()) return null
        return stack.removeLast()
    }
}
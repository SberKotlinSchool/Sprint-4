package ru.sber.generics

// 1.
fun <K, V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return (p1.first == p2.first) && (p1.second == p2.second)
}

// 2.
fun <T : Comparable<T>> countGreaterThan(anArray: Array<T>, elem: T): Int {

    anArray.sort()
    if (anArray[0] > elem) return 0

    anArray.forEachIndexed() { k, v ->
        if (v > elem) return anArray.size - k
    }

    return 0
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
//Реализация с массивом
class StackArray <T>(size: Int) {
    //var list : MutableList<T> = mutableListOf()
    private var pool: Array<T?> = arrayOfNulls<Any?>(3) as Array<T?>
    private var capacity = size;
    private var top = -1

    fun isFull(): Boolean {
        return top == capacity - 1
    }

    fun isEmpty(): Boolean {
        return top == -1
    }

    fun size(): Int {
        return top + 1
    }

    fun peek(): T? {
        return if (!isEmpty()) {
            pool[top]
        } else {
            println("Nothing to show, the stack is empty")
            null
        }
    }

    fun push(elem: T?) {
        if (isFull()) {
            println("Cannot insert element <t> in stack. Stack is full")
        } else {
            println("Element <$elem> inserted")
            top++
            pool[top] = elem
        }
    }

    fun pop(): T? {
        if (isEmpty()) {
            println("Nothing to delete, the stack is empty")
            return null
        } else {
            println("Removing element <${pool[top]}>")
            return pool[top--]
        }

    }
}

class Stack <T> {
    private var pool : MutableList<T> = mutableListOf()
    private var top = -1


    fun isEmpty(): Boolean {
        return pool.isEmpty()
    }

    fun size(): Int {
        return pool.size
    }

    fun peek(): T? {
        return if (!isEmpty()) {
            pool[top]
        } else {
            println("Nothing to show, the stack is empty")
            null
        }
    }

    fun push(elem: T) {
            println("Element <$elem> inserted")
            top++
            pool.add(elem)
    }

    fun pop(): T? {
        if (isEmpty()) {
            println("Nothing to delete, the stack is empty")
            return null
        } else {
            println("Removing element <${pool[top]}>")
            top--
            return pool.removeLast()
        }

    }


}

fun main() {
    val stackArray = StackArray<String>(3)

    stackArray.push("1")
    stackArray.push("2")
    stackArray.push("3")
    println(stackArray.isFull())
    println(stackArray.size())
    stackArray.pop()
    println(stackArray.peek())
    println(stackArray.isEmpty())
    println("=============================")

    val stack = Stack<String>()
    stack.push("1")
    stack.push("2")
    stack.push("3")

    println(stack.size())
    stack.pop()
    println(stack.peek())
    println(stack.isEmpty())

}
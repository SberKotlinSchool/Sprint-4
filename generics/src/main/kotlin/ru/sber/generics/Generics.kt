package ru.sber.generics

//import com.sun.org.apache.xpath.internal.operations.Bool
//import java.util.*


// 1. Реализовать обобщенную функцию compare, которая сравнивала бы два объекта класса Pair p1 и p2 и возвращала значение Boolean.
fun <K,V> compare(p1: Pair<K, V>, p2: Pair<K, V>): Boolean {
    return (p1.first == p2.first) and (p1.second == p2.second)
}

// 2. Реализовать обобщенную функцию, чтобы найти количество элементов в общем массиве, которое больше, чем определенный элемент.
//    int countGreaterThan принимает на вход массив и элемент, с которым нужно сравнить все остальные элементы массива.
fun <T> countGreaterThan(anArray: Array<T>, elem: T): Int where T : Comparable<T> {
    return anArray.count { it > elem }
}

// 3. Реализовать обобщенный класс Sorter с параметром Т и подходящим ограничением, который имеет свойство list:MutableList<T> и
//    функцию fun add(value:T).С каждым вызовом функции передаваемое значение должно добавляться в список и список должен
//    оставаться в отсортированном виде.
class Sorter<T> where T : Comparable<T> {
    val list: MutableList<T> = mutableListOf()

    fun add(value: T) {
        list.add(value)
        list.sort()
    }
}

// 4. Написать обобщенный стек. Минимально - реализовать функции вставки, извлечения и проверки на пустоту.
class Stack<T> (initialSize: Int = 16) {
    /*
        Начальный размер стека по умолчанию - 16. Увеличивается в 2 раза.
        Элементы добавляются в поочередно с 0 по последний в массив, указатель currentIndex указывает на верх стека
        push — добавляет элемент на верх стека. Если стек переполнен - исключение
        pop — удаляет верхний элемент из стека и возвращает его. Если стек пустой - возвращает null
        peek — возвращает верхний элемент стека, но не удаляет его из стека
        isEmpty - проверка стека на пустоту
     */

    private val maxSize = Int.MAX_VALUE
    private val defaultSize = 16
    private var nextIndex = 0
    private var array = arrayOfNulls<Any?>(initialSize)

    private fun increaseSize() {
        if (array.size == maxSize) { throw IllegalStateException("Stack overflow!") }
        val potentialNewSize : Long = (array.size * 2).toLong()
        val newSize = if (potentialNewSize > maxSize.toLong()) { maxSize } else { potentialNewSize }
        val arrayCopy = array.copyOf(newSize.toInt())
        array = arrayCopy
    }

    /** Уменьшение вдвое, если индекс == 0.4 * длина (т.е. заметна тенденция на уменьшение, и можно сэкономить память) */
    private fun checkAndDecreaseSizeIfNeeded() {
        if ((array.size > defaultSize) and (nextIndex <= (0.4 * array.size).toInt())) {
            val arrayCopy = array.copyOfRange(0, array.size / 2)
            array = arrayCopy
        }
    }

    fun push(element: T) {
        if (array.size == nextIndex) { increaseSize() }
        array[nextIndex] = element
        nextIndex++
    }

    fun pop() : T {
        val lastElement = peek()
        array[nextIndex - 1] = null
        nextIndex--
        checkAndDecreaseSizeIfNeeded()
        return lastElement
    }

    fun peek() : T {
        @Suppress("UNCHECKED_CAST")
        if (isEmpty()) { throw IllegalStateException("Stack is empty!") }
        return array[nextIndex - 1] as T
    }

    fun isEmpty() : Boolean {
        return nextIndex == 0
    }
}
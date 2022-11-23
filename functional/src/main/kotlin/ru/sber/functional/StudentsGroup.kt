package ru.sber.functional

import java.util.function.Predicate

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: Predicate<Student>): List<Student> {
        return students.filter { predicate.test(it) }
    }
}

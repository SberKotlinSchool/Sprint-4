package ru.sber.functional

import javax.sql.rowset.Predicate

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean) {
        students = students.filter(predicate)
    }
}

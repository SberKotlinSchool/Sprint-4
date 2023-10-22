package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(filter: (Student)-> Boolean) = students.filter { filter(it) }
}

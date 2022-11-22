package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(preicate: (Student) -> Boolean) = students.filter(preicate)
}

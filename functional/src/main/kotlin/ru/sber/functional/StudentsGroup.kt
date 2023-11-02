package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(filterPredicat: (Student) -> Boolean): List<Student> = students.filter(filterPredicat)
}

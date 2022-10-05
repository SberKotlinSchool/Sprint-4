package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(filter: (Student) -> Boolean): List<Student> {
        return students.filter(filter)
    }

}

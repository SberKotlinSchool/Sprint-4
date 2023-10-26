package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(filterStudents: (Student) -> Boolean) =
            students.filter(filterStudents)

}

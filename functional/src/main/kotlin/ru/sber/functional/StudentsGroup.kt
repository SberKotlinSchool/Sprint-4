package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(par: (Student) -> Boolean): List<Student> {
        return students.filter { par(it) }
    }

    fun init(newList: List<Student>) {
        students = newList
        students = filterByPredicate { student: Student -> student.lastName.isNotEmpty() }
        students = filterByPredicate { student: Student -> student.firstName.isNotEmpty() }
        students = filterByPredicate { student: Student -> !student.averageRate.isNaN() }
    }

}

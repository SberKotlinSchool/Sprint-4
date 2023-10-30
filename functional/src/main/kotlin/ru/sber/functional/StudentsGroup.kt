package ru.sber.functional

const val RATE: Double = 3.0

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(filter: (Student) -> Boolean) {
        students = students.filter(filter)
    }
}

fun main() {
    val studentsGroup = StudentsGroup()
    studentsGroup.students = getStudentsList()
    studentsGroup.filterByPredicate { student: Student -> student.averageRate > RATE }
    print(studentsGroup.students)
}

fun getStudentsList(): List<Student> {
    return listOf(
        Student(firstName = "fn1", lastName = "ln1", averageRate = 5.0),
        Student(firstName = "fn2", lastName = "ln2", averageRate = 4.0),
        Student(firstName = "fn3", lastName = "ln3", averageRate = 3.0),
        Student(firstName = "fn4", lastName = "ln4", averageRate = 2.0),
        Student(firstName = "fn5", lastName = "ln5", averageRate = 1.0),
    )
}
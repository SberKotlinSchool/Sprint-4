package ru.sber.functional

import kotlin.random.Random

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(filter: (Student) -> Boolean): List<Student> {
        return students.filter(filter)
    }
}

fun main() {

    val studentsGroup = StudentsGroup()
    val st1 = Student(firstName = "St1Name", lastName = "St1Surname", age = Random.nextInt(0, 45))
    val st2 = Student(firstName = "St2Name", lastName = "St2Surname", age = Random.nextInt(0, 45))
    val st3 = Student(firstName = "St3Name", lastName = "St3Surname", age = Random.nextInt(0, 45))
    val st4 = Student(firstName = "St4Name", lastName = "St4Surname", age = Random.nextInt(0, 45))

    studentsGroup.students = listOf(st1, st2, st3, st4)

    val filter: (Student) -> Boolean = { student -> student.firstName == "St1Name" }
    val filter2: (Student) -> Boolean = { student -> student.age > 30 }
    println("filter1 ${studentsGroup.filterByPredicate(filter)}")
    println("filter2 ${studentsGroup.filterByPredicate(filter2)}")
}

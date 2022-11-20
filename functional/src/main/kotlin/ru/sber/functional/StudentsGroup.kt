package ru.sber.functional

import kotlin.random.Random

class StudentsGroup {
    lateinit var students: MutableList<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean) = students.filter(predicate)

    fun generateStudents(amount: Int) {
        this.students = mutableListOf()

        for (i in 1..amount) {
            students.add(
                Student(
                firstName = "Студент$i",
                lastName = "Фамилия$i",
                middleName = "Отчество$i",
                averageRate = Random.nextDouble(2.0, 5.0)
                )
            )
        }

        students.add(
            Student(
                firstName = "СтудентСпец",
                lastName = "ФамилияСпец",
                middleName = "ОтчествоСпец",
                averageRate = Random.nextDouble(2.0, 5.0),
                age = 24
            )
        )
    }
}

fun main() {
    val studentsGroup = StudentsGroup()
    studentsGroup.generateStudents(10)

    studentsGroup.filterByPredicate { it.age == 24 }


}

package ru.sber.functional

import kotlin.random.Random

class StudentsGroup {

    var students: List<Student> = listOf(
        Student(firstName = "Джон", lastName = "Сильвер", averageRate = Random.nextDouble(1.0, 5.0)),
        Student(firstName = "Билли", lastName = "Бонс", averageRate = Random.nextDouble(1.0, 5.0)),
        Student(firstName = "Абрахам", lastName = "Грей", averageRate = Random.nextDouble(1.0, 5.0)),
        Student(firstName = "Джим", lastName = "Хокинс", averageRate = Random.nextDouble(1.0, 5.0))
    )

    fun filterByPredicate(predicate: (Student) -> Boolean) {
        this.students = this.students.filter(predicate = predicate)
    }

}

fun main() {
    val studentGroup = StudentsGroup()
    for (student in studentGroup.students) {
        println(student)
    }
}
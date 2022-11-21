package ru.sber.functional

import kotlin.math.roundToInt
import kotlin.random.Random

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        return students.filter(predicate)
    }

}

// Демонстрация работы
fun main() {
    // Набираем студентов с произвольным именем и успеваемостью
    val students: ArrayList<Student> = ArrayList()
    for (i in 1..20) {
        students.add(
            Student(
                "${(Random.nextInt(0, 25) + 'a'.code).toChar()}",
                averageRate = (Random.nextDouble(3.0, 5.0) * 100).roundToInt() / 100.0
            )
        )
    }
    println("Полный список студентов. Количество: ${students.size}")
    students.forEach { println(it) }
    println()

    // Создаём группу со студентами
    val studentsGroup = StudentsGroup()
    studentsGroup.students = students

    // Задаём условие по успеваемости
    val studentsFilteredByRate = studentsGroup.filterByPredicate { it.averageRate >= 4.5 }
    println("Студенты, отфильтрованные по успеваемости. Количество: ${studentsFilteredByRate.size}")
    studentsFilteredByRate.forEach { println(it) }
    println()

    // Задаём условие по имени
    val firstLetter = "q"
    val studentsFilteredByName = studentsGroup.filterByPredicate { it.firstName.startsWith(firstLetter) }
    println("Студенты, у которых имя начинается с \'$firstLetter\'. Количество: ${studentsFilteredByName.size}")
    studentsFilteredByName.forEach { println(it) }
}

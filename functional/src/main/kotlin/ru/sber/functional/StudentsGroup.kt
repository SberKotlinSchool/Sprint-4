package ru.sber.functional

import kotlin.random.Random

class StudentsGroup {
    lateinit var students: MutableList<Student>

    //понимаю, что predicate можно было просто передать в метод filter, думал, что надо реализовать что-то свое
    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        var filteredList: MutableList<Student> = mutableListOf()
         students.forEach {
             if (predicate(it))
                 filteredList.add(it)
        }
        return filteredList
    }

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

//    fun printStudents() {
//        println("firstName | lastName | middleName | averageRate")
//        students.forEach {
//            println("${it.firstName}| ${it.lastName} | ${it.middleName} | ${it.averageRate}")
//        }
//    }
}

fun main() {
    val studentsGroup = StudentsGroup()
    studentsGroup.students = mutableListOf()
    studentsGroup.generateStudents(10)

    studentsGroup.filterByPredicate { it.age != null }.forEach {
        println("${it.firstName} | ${it.lastName} | ${it.middleName} | ${it.averageRate}")
    }

//    studentsGroup.filterByPredicate { it.averageRate > 3.0 }.forEach {
//        println("${it.firstName}| ${it.lastName} | ${it.middleName} | ${it.averageRate}")
//    }
//
//    studentsGroup.filterByPredicate { it.lastName.contains("2") }.forEach {
//        println("${it.firstName}| ${it.lastName} | ${it.middleName} | ${it.averageRate}")
//    }
}

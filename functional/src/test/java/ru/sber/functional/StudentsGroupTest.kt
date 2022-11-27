package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StudentsGroupTest {

    @Test
    fun `filterByPredicate возраст меньше 50`() {
        val groupA = StudentsGroup()
        groupA.students = listOf(
            Student(lastName = "Иванов", firstName = "Иван", age = 22, averageRate = 10.0),
            Student(lastName = "Петров", firstName = "Василий", averageRate = 11.2),
            /*---->>>>> */Student(lastName = "Муравьев-Амурский", firstName = "Федор", age = 213, averageRate = 60.0),
            Student(lastName = "Сидоров", firstName =  "Петр", age = 18, averageRate = 13.2),
            Student(lastName = "Макарова", firstName = "Елизавета", age = 19, averageRate = 41.0)
        )
        val groupTest = StudentsGroup()
        groupTest.students = listOf(
            Student(lastName = "Иванов", firstName = "Иван", age = 22, averageRate = 10.0),
            Student(lastName = "Петров", firstName = "Василий", averageRate = 11.2),
            Student(lastName = "Сидоров", firstName =  "Петр", age = 18, averageRate = 13.2),
            Student(lastName = "Макарова", firstName = "Елизавета", age = 19, averageRate = 41.0)
        )

        println(groupA.students.toString())

        assertEquals(groupTest.students, groupA.filterByPredicate { it.age < 50 })
    }
}
package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class StudentsGroupTest {

    private val sg = StudentsGroup()


    @Test
    fun filterByPredicate1() {
        sg.students = listOf(
            Student(firstName = "Alex", lastName = "Alex", averageRate = 4.0),
            Student(firstName = "Ivan", lastName = "Ivanov", averageRate = 4.5),
            Student(firstName = "Petr", lastName = "Petrov", averageRate = 4.3),
            Student(firstName = "Serj", lastName = "Serj", averageRate = 4.8),
            Student(firstName = "Fedor", lastName = "Fedorov", averageRate = 3.0),
            Student(firstName = "Nik", lastName = "Nik", averageRate = 3.5),
            Student(firstName = "Edward", lastName = "Edward", averageRate = 3.8)
        )
        val listResult = sg.filterByPredicate { it:Student -> it.averageRate > 4.0}
        assertEquals(3, listResult.size)
    }

    @Test
    fun filterByPredicate2() {
        sg.students = listOf(
            Student(firstName = "Alex", lastName = "Alex", averageRate = 4.0),
            Student(firstName = "Ivan", lastName = "Ivanov", averageRate = 4.5),
            Student(firstName = "Petr", lastName = "Petrov", averageRate = 4.3),
            Student(firstName = "Serj", lastName = "Serj", averageRate = 4.8),
            Student(firstName = "Fedor", lastName = "Fedorov", averageRate = 3.0),
            Student(firstName = "Nik", lastName = "Nik", averageRate = 3.5),
            Student(firstName = "Edward", lastName = "Edward", averageRate = 3.8)
        )
        val listResult = sg.filterByPredicate { it:Student -> it.firstName[0] <= 'N'}
        assertEquals(5, listResult.size)
    }
}
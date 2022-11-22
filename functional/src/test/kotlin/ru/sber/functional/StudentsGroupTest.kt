package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StudentsGroupTest {

    private val allStudents = listOf(
        Student(firstName = "Noah", lastName = "Smith", averageRate = 1.0),
        Student(firstName = "Liam", lastName = "Johnson", averageRate = 2.0),
        Student(firstName = "Mason", lastName = "Williams", averageRate = 3.0),
        Student(firstName = "Jacob", lastName = "Jones", averageRate = 4.0),
        Student(firstName = "William", lastName = "Brown", averageRate = 5.0),
    )

    @Test
    fun `filterByPredicate() success test`() {
        val studentsGroup = StudentsGroup()
        studentsGroup.students = allStudents
        val filteredStudents = studentsGroup.filterByPredicate { it.averageRate > 3.0 }
        assertEquals(2, filteredStudents.size)
        assertEquals(listOf(allStudents[3], allStudents[4]), filteredStudents)
    }
}
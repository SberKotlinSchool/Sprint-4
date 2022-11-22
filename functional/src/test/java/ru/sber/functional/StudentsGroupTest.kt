package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StudentsGroupTest {

    @Test
    fun filterByPredicate() {
        val studentsGroup = StudentsGroup()
        val students = studentsGroup.students

        studentsGroup.init()

        val filterByFirstName = studentsGroup.filterByPredicate { student: Student -> student.firstName == "Va"}
        val filterByLastName = studentsGroup.filterByPredicate { student: Student -> student.lastName == "vos"}
        val filterByAverageRate = studentsGroup.filterByPredicate { student: Student -> student.averageRate > 2}

        assertEquals(students[0], filterByFirstName[0])
        assertEquals(students[1], filterByLastName[0])
        assertEquals(students.subList(0,3), filterByAverageRate)
    }
}
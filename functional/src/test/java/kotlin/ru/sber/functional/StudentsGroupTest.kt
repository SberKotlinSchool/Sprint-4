package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StudentsGroupTest {

    @Test
    fun filterByPredicate() {
        val studentsGroup = StudentsGroup()

        assertEquals(3, studentsGroup.students.size)
        studentsGroup.filterByPredicate { it.averageRate > 4.6 }
        assertEquals(2, studentsGroup.students.size)
    }
}
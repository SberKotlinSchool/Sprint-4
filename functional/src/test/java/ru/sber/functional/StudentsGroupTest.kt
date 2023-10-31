package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StudentsGroupTest {

    @Test
    fun filterByPredicate() {
        val studentsGroup = StudentsGroup()
        studentsGroup.filterByPredicate { it.averageRate > 4.0 }
        assertEquals(1, studentsGroup.students.size)
    }
}
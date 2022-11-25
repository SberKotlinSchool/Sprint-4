package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StudentsGroupTest {

    @Test
    fun filterByPredicate() {
        val studentsGroup = StudentsGroup()

        assertEquals(4, studentsGroup.students.size)
        studentsGroup.filterByPredicate { (it.firstName == "Джон") || (it.lastName == "Бонс") }
        assertEquals(2, studentsGroup.students.size)
    }
}
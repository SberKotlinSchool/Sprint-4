package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StudentsGroupTest {

    @Test
    fun filterByPredicate() {
        val studentsGroup = StudentsGroup()
        studentsGroup.filterByPredicate{ it.lastName == "Иванов"}
        assertEquals(studentsGroup.students.size, 1)
        assertEquals(studentsGroup.students[0].firstName, "Иван")
    }
}
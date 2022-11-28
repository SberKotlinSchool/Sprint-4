package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class StudentsGroupTest {

    @Test
    internal fun filterByPredicateTest() {
        val studentsGroup = StudentsGroup()
        val studentList = studentsGroup.filterByPredicate { it.lastName == "Иванов"}
        assertEquals(studentList.size, 1)
        assertEquals(studentList[0].firstName, "Иван")
    }
}
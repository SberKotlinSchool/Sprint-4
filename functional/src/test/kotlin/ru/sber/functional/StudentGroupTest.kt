package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


internal class StudentsGroupTest {

    @Test
    fun filterByPredicate() {
        val studentsGroup = StudentsGroup()
        studentsGroup.init()

        val filterByFirstName = studentsGroup.filterByPredicate { student: Student -> student.firstName.contains("Ива") }
        val filterByLastName = studentsGroup.filterByPredicate { student: Student -> student.lastName.contains("си", true) }
        val filterByAverageRate = studentsGroup.filterByPredicate { student: Student -> student.averageRate > 3 }

        assertTrue(filterByFirstName.size == 3)
        assertTrue(filterByLastName.size == 3)
        assertTrue(filterByAverageRate.size == 4)
    }
}

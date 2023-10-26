package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StudentsGroupTest {

    @Test
    fun filterByPredicateTest() {
        val studentsGroup = StudentsGroup()
        val filterByAverageRate = studentsGroup.filterByPredicate { student -> student.averageRate == 4.5 }
        assertEquals(2, filterByAverageRate.size)
        val filterByAge = studentsGroup.filterByPredicate { student -> student.age == 18 }
        assertEquals(emptyList<Student>(), filterByAge)
    }
}
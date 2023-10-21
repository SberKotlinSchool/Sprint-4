package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StudentsGroupTest {

    @Test
    fun `filterByPredicate should return students with averageRate greater than 4`() {
        val group = StudentsGroup()

        val filteredStudents = group.filterByPredicate { it.averageRate > 4.0 }

        assertEquals(2, filteredStudents.size)
        assertTrue(filteredStudents.any { it.firstName == "Иван" })
        assertTrue(filteredStudents.any { it.firstName == "Сергей" })
    }

    @Test
    fun `filterByPredicate should return students with city as Москва`() {
        val group = StudentsGroup()

        val filteredStudents = group.filterByPredicate { it.city == "Москва" }

        assertEquals(3, filteredStudents.size)
    }

    @Test
    fun `filterByPredicate should return students with specialization as Информационные технологии`() {
        val group = StudentsGroup()

        val filteredStudents = group.filterByPredicate { it.specialization == "Информационные технологии" }

        assertEquals(3, filteredStudents.size)
    }

    @Test
    fun `filterByPredicate should return students with previous education as Нейронные сети`() {
        val group = StudentsGroup()

        val filteredStudents = group.filterByPredicate { it.prevEducation == "Нейронные сети" }

        assertEquals(3, filteredStudents.size)
    }
}

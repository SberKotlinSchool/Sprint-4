package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StudentsGroupTest {
    private lateinit var studentsGroup: StudentsGroup

    @BeforeEach
    fun `init`() {
        studentsGroup = StudentsGroup().apply {
            val student1 = Student(firstName = "Василий", lastName = "Пупкин", averageRate = 4.7)
            val student2 = Student(firstName = "ВасилийВасилий", lastName = "ПупкинПупкин", averageRate = 3.5)
            val student3 = Student(firstName = "Коля", lastName = "Тяпкин", averageRate = 5.7)
            students = listOf(student1, student2, student3)
        }
    }

    @Test
    fun `test filterByPredicate then lastName length equals 6`() {
        val students = studentsGroup.filterByPredicate { student: Student -> student.lastName.length == 6 }
        assertEquals(2, students.size)
    }

    @Test
    fun `test filterByPredicate then average less 4 8`() {
        val students = studentsGroup.filterByPredicate { student: Student -> student.averageRate < 4.8 }
        assertEquals(2, students.size)
    }
}
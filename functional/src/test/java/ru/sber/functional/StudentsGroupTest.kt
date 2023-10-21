package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StudentsGroupTest {

    @Test
    fun `test filterByPredicate then lastName length equals 6`() {
        val studentsGroup = StudentsGroup().apply {
            students = getStudents()
        }
        val students = studentsGroup.filterByPredicate { student: Student -> student.lastName.length == 6 }
        assertEquals(2, students.size)
    }


    @Test
    fun `test filterByPredicate then average less 4 8`() {
        val studentsGroup = StudentsGroup().apply {
            students = getStudents()
        }
        val students = studentsGroup.filterByPredicate { student: Student -> student.averageRate < 4.8 }
        assertEquals(2, students.size)
    }

    private fun getStudents(): List<Student> {
        val student1 = Student(firstName = "Василий", lastName = "Пупкин", averageRate = 4.7)
        val student2 = Student(firstName = "ВасилийВасилий", lastName = "ПупкинПупкин", averageRate = 3.5)
        val student3 = Student(firstName = "Коля", lastName = "Тяпкин", averageRate = 5.7)
        return listOf(student1, student2, student3)
    }
}
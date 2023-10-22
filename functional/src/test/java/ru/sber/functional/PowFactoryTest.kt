package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PowFactoryTest {
    @Test
    fun `buildPowFunction should return lambda It should calculate to second power`() {
         assertEquals(9, PowFactory.buildPowFunction(2)(3))
    }

    @Test
    fun `filtering students average scores`() {
        val studentsGroup = StudentsGroup()
        studentsGroup.students = listOf(
                Student(firstName = "Иван", lastName = "Иванов", averageRate = 4.0),
                Student(firstName = "Петр", lastName = "Петров", averageRate = 3.0),
                Student(firstName = "Роман", lastName = "Романов", averageRate = 5.0),
                Student(firstName = "Оксана", lastName = "Кучкова", averageRate = 4.5)
        )

        val filterStudent = studentsGroup.filterByPredicate { student: Student -> student.averageRate > 4.0 }

        assertEquals(filterStudent, listOf(
                Student(firstName = "Роман", lastName = "Романов", averageRate = 5.0),
                Student(firstName = "Оксана", lastName = "Кучкова", averageRate = 4.5))
        )
    }
}

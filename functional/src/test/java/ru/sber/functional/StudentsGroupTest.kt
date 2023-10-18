package ru.sber.functional

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class StudentsGroupTest {

    private val studentsGroup = StudentsGroup().also {
        it.students = listOf(
            Student(lastName = "Иванов", firstName = "Иван", averageRate = 5.0),
            Student(lastName = "Петров", firstName = "Петр", averageRate = 3.5),
            Student(lastName = "Сидоров", firstName = "Сидр", averageRate = 4.2),
            Student(lastName = "Сидорова", firstName = "Светлана", averageRate = 3.9),
            Student(lastName = "Иванова", firstName = "Екатерина", averageRate = 4.6),
            Student(lastName = "Иванов", firstName = "Антон", averageRate = 4.5),
            Student(lastName = "Иванов", firstName = "Александр", averageRate = 4.7)
        )
    }

    @Test
    fun `filter group`() {
        //given
        val expectedResult: List<Student> = listOf(
            Student(lastName = "Иванов", firstName = "Иван", averageRate = 5.0)
        )
        //when
        val result = studentsGroup.filterByPredicate { it.lastName == "Иванов" && it.averageRate > 4.7 }
        //then
        assertContentEquals(expectedResult, result)
    }

    @Test
    fun `filter group2`() {
        //given
        val expectedResult: List<Student> = listOf(
            Student(lastName = "Иванов", firstName = "Антон", averageRate = 4.5)
        )
        //when
        val result = studentsGroup.filterByPredicate { it.lastName == "Иванов" && it.firstName == "Антон" }
        //then
        assertContentEquals(expectedResult, result)
    }

    @Test
    fun `filter group3`() {
        //given
        val expectedResult: List<Student> = listOf(
            Student(lastName = "Иванов", firstName = "Иван", averageRate = 5.0),
            Student(lastName = "Сидоров", firstName = "Сидр", averageRate = 4.2),
            Student(lastName = "Иванова", firstName = "Екатерина", averageRate = 4.6),
            Student(lastName = "Иванов", firstName = "Антон", averageRate = 4.5),
            Student(lastName = "Иванов", firstName = "Александр", averageRate = 4.7)
        )
        //when
        val result = studentsGroup.filterByPredicate { it.averageRate > 4 }
        //then
        assertContentEquals(expectedResult.sortedBy { it.averageRate }, result.sortedBy { it.averageRate })
    }

    @Test
    fun `filter group throw exception`() {
        //given
        val studentsGroup = StudentsGroup()
        //when
        //then
        Assertions.assertThrows(UninitializedPropertyAccessException::class.java) {
            studentsGroup.filterByPredicate { it.lastName == "Иванов" }
        }
    }
}
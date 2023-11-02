package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StudentsGroupTest {

    @Test
    fun `filterByPredicate should return filtered list`() {
        // given
        val studentsList = listOf(
            Student(firstName = "Петр", lastName = "Петров", middleName = "Петрович", age = 25, averageRate = 4.0),
            Student(firstName = "Василий", lastName = "Васильев", middleName = "Васильеич", age = 98, averageRate = 2.5),
            Student(firstName = "Иван", lastName = "Иванов", middleName = "Иванович", age = 21, city = "Москва", averageRate = 1.8),
            Student(firstName = "Алексей", lastName = "Алексеев", middleName = "Алексеевич", age = 20, averageRate = 4.1)
        )
        val group = StudentsGroup().apply {
            students = studentsList
        }

        // when
        val result = group.filterByPredicate { it.age >= 25 }

        // then
        assertEquals(2, result.size)
    }
}
package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StudentsGroupTest {

    @Test
    fun `filterByPredicate should return filtered list`() {
        // given
        val (student1, student2, student3, student4) = listOf(
            Student(firstName = "Кирилл", lastName = "Иванов", averageRate = 4.4),
            Student(firstName = "Иван", lastName = "Сергеев", averageRate = 3.5),
            Student(firstName = "Анна", lastName = "Петрова", city = "Москва", averageRate = 4.8),
            Student(firstName = "Алексей", lastName = "Курочкин", averageRate = 3.1)
        )
        val group = StudentsGroup().apply {
            students = listOf(student1, student2, student3, student4)
        }

        // when
        val result = group.filterByPredicate { it.averageRate > 4 }

        // then
        assertEquals(listOf(student1, student3), result)
    }
}
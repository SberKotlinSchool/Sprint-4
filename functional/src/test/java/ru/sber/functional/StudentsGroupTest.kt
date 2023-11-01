package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StudentsGroupTest {

    @Test
    fun `filter students by predicate`() {
        val student1 = Student("Иван", "Иванов", "Иванович", 20, 4.2, "Москва", "Программная инженерия", null)
        val student2 = Student("Петров", "Петр", "Иванович", 20, 3.9, "Москва", "Программная инженерия", null)
        val student3 = Student("Сидоров", "Геннадий", "Иванович", 20, 4.0, "Москва", "Программная инженерия", null)
        val student4 = Student("Воробьев", "Денис", "Иванович", 20, 2.8, "Москва", "Программная инженерия", null)
        val student5 = Student("Петухов", "Тимофей", "Иванович", 20, 3.5, "Москва", "Программная инженерия", null)
        val student6 = Student("Голубева", "Анна", "Иванович", 20, 4.75, "Москва", "Программная инженерия", null)

        val group = StudentsGroup(listOf(student1, student2, student3, student4, student5, student6))
        val filtered = group.filterByPredicate { it.averageRate >= 4.0 }

        assertEquals(filtered, listOf(student1, student3, student6))
    }

}
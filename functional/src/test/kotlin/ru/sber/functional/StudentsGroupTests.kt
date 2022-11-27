package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class StudentsGroupTests {

    @Test
    fun `test basic filter group`() {
        val group = StudentsGroup()
        val expected = listOf(
            Student("Андрей", "Смирнов", 4.5),
            Student("Иван", "Васильев", 5.0)
        )

        group.filterByPredicate { it.averageRate > 4.0 }

        assertContentEquals(expected, group.students)
    }

    @Test
    fun `filter group`() {
        val testStudents = listOf(
            Student("Александр", "Алехин", 3.0, age = 24, city = "Moscow"),
            Student("Думчев", "Артур", 5.0, age = 31, city = "Moscow"),
            Student("Андрей", "Смирнов", 4.5, city = "Cheboksary"),
            Student("Иван", "Васильев", 5.0),
            Student("Иван", "Васильев", 5.0)
        )

        val group = StudentsGroup().apply { students = testStudents }

        val expected = listOf(
            Student("Александр", "Алехин", 3.0, age = 24, city = "Moscow"),
            Student("Думчев", "Артур", 5.0, age = 31, city = "Moscow"),
        )
        group.filterByPredicate { it.age != null && it.city == "Moscow" }

        assertContentEquals(expected, group.students)
    }
}

package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StudentsGroupTest {

    @Test
    fun `check filterByPredicate`() {
        val group = StudentsGroup()
        group.students = listOf(
                student("Kotlin"),
                student("Kotlin"),
                student("Java"),
                student("Python"),
                student("Go"),
                student("JS")
        )

        assertEquals(listOf(student("Java")), group.filterByPredicate { it.specialization == "Java" })
        assertEquals(listOf(student("Kotlin"), student("Kotlin")), group.filterByPredicate { it.specialization == "Kotlin" })
    }

    private fun student(spec: String) =
            Student(firstName = "Имя",
                    lastName = "Фамилия",
                    averageRate = 4.0,
                    specialization = spec
            )

}
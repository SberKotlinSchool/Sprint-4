package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test

internal class StudentsGroupTest {
    lateinit var studentGroup: StudentsGroup

    @BeforeEach
    fun setUp(){
        studentGroup = StudentsGroup()
    }
    @Test
    fun filterByPredicate() {
        val expectedStudents = listOf(
            Student("Пётр", "Петров", 5.0),
            Student("Юлия", "Иванова", 4.8)
        )

        val actualStudents = studentGroup.filterByPredicate { it.averageRate > 4.5 }

        assertIterableEquals(expectedStudents, actualStudents)
    }
}

package ru.sber.functional

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StudentsGroupTest {
    private val studentsGroup = StudentsGroup()

    // FIXME не удалось заставить работать BeforeAll
    private val student1 = Student("Иван", "Иванов", "Иванович", 19, 3.7)
    private val student2 =
        Student(city = "Москва", firstName = "Петр", lastName = "Петров", averageRate = 4.7)
    private val student3 = Student(
        city = "Москва",
        firstName = "Сидор",
        lastName = "Сидоров",
        averageRate = 4.8,
        middleName = "Сидорович",
        prevEducation = "Школа",
        specialization = "Финансы",
        age = 20
    )

    @BeforeEach
    fun setUp() {
        studentsGroup.students = listOf(student1, student2, student3)
    }

    @Test
    fun filterByPredicate() {
        studentsGroup.filterByPredicate { it.averageRate > 4.7 }

        assertEquals(listOf(student3), studentsGroup.students)
    }

    @Test
    fun filterByAlwaysTruePredicate() {
        studentsGroup.filterByPredicate { true }

        assertEquals(listOf(student1, student2, student3), studentsGroup.students)
    }

    @Test
    fun filterByAlwaysFalsePredicate() {
        studentsGroup.filterByPredicate { false }

        assertEquals(emptyList(), studentsGroup.students)
    }
}
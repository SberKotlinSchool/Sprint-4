package ru.sber.functional

import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentsGroupTest {
    private val studentsGroup = StudentsGroup()
    private lateinit var student1: Student
    private lateinit var student2: Student
    private lateinit var student3: Student

    @BeforeAll
    fun setUpAll() {
        student1 = Student("Иван", "Иванов", "Иванович", 19, 3.7)
        student2 = Student(city = "Москва", firstName = "Петр", lastName = "Петров", averageRate = 4.7)
        student3 = Student(
            city = "Москва",
            firstName = "Сидор",
            lastName = "Сидоров",
            averageRate = 4.8,
            middleName = "Сидорович",
            prevEducation = "Школа",
            specialization = "Финансы",
            age = 20
        )
    }

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
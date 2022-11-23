package ru.sber.functional

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.stream.IntStream
import kotlin.random.Random
import kotlin.streams.toList
import kotlin.test.assertEquals

internal class StudentsGroupTest {

    val NUMBER_OF_STUDENTS = 1001
    val studentsGroup = StudentsGroup()

    @BeforeEach
    fun setUp() {
        studentsGroup.students = IntStream.rangeClosed(1, NUMBER_OF_STUDENTS)
            .mapToObj { Student(
                firstName = "firstName$it",
                lastName = "lastname$it",
                middleName = "middleName$it",
                age = 18 + Random.nextInt(5),
                // каждый 2-й студент имеет средний балл > 4
                averageRate = 4.0 + if (it % 2 == 0) Random.nextDouble(1.0) else -Random.nextDouble(1.0),
                // всего 5 городов
                city = "city${it % 5 + 1}",
                specialization = "specialization$it",
                // каждый 5-й уже имеет одно высшее образование
                prevEducation = if (it % 5 == 0) "высшее" else "среднее"
            ) }.toList()
    }

    @Test
    fun filterByAverageRateTest() {
        val filteredStudents = studentsGroup.filterByPredicate { it.averageRate > 4.0 }
        assertEquals(NUMBER_OF_STUDENTS / 2, filteredStudents.size)
    }

    @Test
    fun filterByCityTest() {
        val filteredStudents = studentsGroup.filterByPredicate { it.city == "city1" }
        assertEquals(NUMBER_OF_STUDENTS / 5, filteredStudents.size)
    }

    @Test
    fun filterByPrevEducationTest() {
        val filteredStudents = studentsGroup.filterByPredicate { it.prevEducation == "высшее" }
        assertEquals(NUMBER_OF_STUDENTS / 5, filteredStudents.size)
    }
}
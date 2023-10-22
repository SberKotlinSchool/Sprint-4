package ru.sber.functional

import io.mockk.mockkObject
import io.mockk.unmockkAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class StudentsGroupTest {

    val studentsGroup: StudentsGroup = StudentsGroup()

    @BeforeEach
    fun setUp() {
        mockkObject(StudentsGroup())
        studentsGroup.students = getStudentsList()
    }

    @AfterEach
    fun closeUp() {
        unmockkAll()
    }

    @Test
    fun shouldFilterByTeenager() {
        studentsGroup.filterByPredicate { student: Student -> student.age <= TEENAGER_AGE }
        assertEquals(5, studentsGroup.students.size)
    }

    @Test
    fun shouldFilterOlderThenTeenager() {
        studentsGroup.filterByPredicate { student: Student -> student.age > TEENAGER_AGE }
        assertEquals(3, studentsGroup.students.size)
    }

    @Test
    fun shouldFilterByLivingInSochi() {
        studentsGroup.filterByPredicate { student: Student -> student.city == "Sochi" }
        assertEquals(1, studentsGroup.students.size)
    }

    @Test
    fun shouldFilterByAverageRate8() {
        studentsGroup.filterByPredicate { student: Student -> student.averageRate >= 8.0 }
        assertEquals(1, studentsGroup.students.size)
    }

}
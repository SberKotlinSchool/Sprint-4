package ru.sber.functional

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.random.Random
import kotlin.test.assertEquals

@DisplayName("StudentsGroupTest class test cases")
internal class StudentsGroupTest {


    @Test
    fun `StudentsGroupTest filterByPredicate test` () {
        val studentsGroup = StudentsGroup()
        val st1 = Student(firstName = "St1Name", lastName = "St1Surname", age = Random.nextInt(0, 45))
        val st2 = Student(firstName = "St2Name", lastName = "St2Surname", age = Random.nextInt(0, 45))
        studentsGroup.students = listOf(st1,st2)
        val filter: (Student) -> Boolean = { student -> student.firstName == "St1Name" }
        val result: List<Student> = studentsGroup.filterByPredicate(filter)
        assertEquals(1,result.size)
        assertEquals("St1Name", result[0].firstName)
    }
}
package ru.sber.functional


import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class StudentsGroupTest {
    private val studentsGroup = StudentsGroup()

    @ParameterizedTest(name = "{index}): {argumentsWithNames}")
    @MethodSource("getDataForFilterByPredicate")
    fun `filterByPredicate should filter students`(predicate: (Student) -> Boolean, listOfStudents: List<Student>) {
        assertEquals(
            studentsGroup.filterByPredicate(predicate), listOfStudents
        )
    }

    private fun getDataForFilterByPredicate(): Stream<Arguments> {
        val student1 = Student(firstName = "Даниил", lastName = "Леонов", averageRate = 5.0)
        val student2 = Student(firstName = "Иван", lastName = "Романов", averageRate = 4.9)
        val student3 = Student(firstName = "Петр", lastName = "Романов", averageRate = 4.8)
        return Stream.of(
            Arguments.of(
                { s: Student -> s.firstName == "Даниил" },
                listOf(student1)
            ),
            Arguments.of(
                { s: Student -> s.averageRate == 4.9 },
                listOf(student2)
            ),
            Arguments.of(
                { s: Student -> s.lastName == "Романов" },
                listOf(student2, student3)
            )
        )
    }
}


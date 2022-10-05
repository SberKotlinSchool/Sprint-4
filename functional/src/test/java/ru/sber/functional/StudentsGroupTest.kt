package ru.sber.functional

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class StudentsGroupTest {

    private val studentsGroup: StudentsGroup = StudentsGroup()

    init {
        studentsGroup.students = listOf(
            Student("Name1", "LastName1", averageRate = 5.0),
            Student("Name2", "LastName2", averageRate = 4.8),
            Student("Name3", "LastName3", averageRate = 4.5),
            Student("Name4", "LastName4", averageRate = 4.1),
        )
    }

    private fun getFilterByPredicateData(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(
                { it: Student -> it.averageRate > 4.5 },
                listOf(
                    Student("Name1", "LastName1", averageRate = 5.0),
                    Student("Name2", "LastName2", averageRate = 4.8),
                ),
                { it: Student -> it.firstName == "LastName2" },
                listOf(
                    Student("Name2", "LastName2", averageRate = 4.8),
                )
            )
        )
    }

    @MethodSource("getFilterByPredicateData")
    @ParameterizedTest
    fun filterByPredicate(filter: (Student) -> Boolean, expected: List<Student>) {

        val actual = studentsGroup.filterByPredicate(filter)

        assertEquals(expected, actual)
    }
}
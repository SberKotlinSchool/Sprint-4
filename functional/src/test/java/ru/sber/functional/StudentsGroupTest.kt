package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class StudentsGroupTest {

    @Test
    fun filterByPredicate() {
        val studentsGroup = StudentsGroup()
        assertEquals(2, studentsGroup.filterByPredicate { it.averageRate > 4.0 }.count() )
        assertEquals(2, studentsGroup.filterByPredicate { it.age >= 60 }.count() )
        assertEquals(2, studentsGroup.filterByPredicate { it.firstName.contains("gey") or it.lastName.contains("in") }.count() )
    }
}
package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class StudentsGroupTest {

    @Test
    fun countfilterByPredicateTest() {
        assertEquals(1,StudentsGroup().filterByPredicate { it.firstName == "Вячеслав" }.count())
        assertEquals(3, StudentsGroup().filterByPredicate { it.averageRate > 6 }.count())
        assertEquals(1,StudentsGroup().filterByPredicate { it.age ==18 }.count())
    }
}
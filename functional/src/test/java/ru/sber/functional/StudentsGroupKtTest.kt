package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StudentsGroupKtTest {

    @Test
    fun filterByPredicateTest() {
        val filtered = StudentsGroup() filterByPredicate {it.averageRate > 3.0}
        assertEquals(filtered.size, 2)
    }
}


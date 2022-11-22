package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class StudentsGroupTest {

    @Test
    fun filterByPredicate() {
        assertEquals(3, StudentsGroup().filterByPredicate { it.averageRate > 5 }.count())
        assertEquals(1, StudentsGroup().filterByPredicate { "Иван" == it.firstName }.count())
        assertEquals(3, StudentsGroup().filterByPredicate { it.age > 10 }.size)
    }
}
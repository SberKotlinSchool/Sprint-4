package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

class StudentsGroupTest {

    @Test
    fun filterByPredicate(){
        val studentGroup = StudentsGroup()
        assertEquals(10, studentGroup.students.size)
        studentGroup.filterByPredicate { it.age > 24 }
        assertEquals(0, studentGroup.students.size)
    }
}
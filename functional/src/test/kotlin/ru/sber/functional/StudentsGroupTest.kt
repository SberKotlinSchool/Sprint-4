package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class StudentsGroupTest {
    val studentsGroup = StudentsGroup()

    @BeforeAll
    fun init(){
        studentsGroup.students = arrayListOf(
            Student(age = 20, lastName = "Uasya",firstName = "U", averageRate = 4.9)
            ,Student(age = 0, lastName = "Uasya",firstName = "U", averageRate = 0.5)
        )
    }

    @Test
    fun filterByPredicate() {
        val ageList = studentsGroup.filterByPredicate { it.age > 0 }
        val lastNameList = studentsGroup.filterByPredicate { it.lastName.isNotEmpty() }
        assertEquals(1, ageList.size)
        assertEquals(2, lastNameList.size)
    }
}
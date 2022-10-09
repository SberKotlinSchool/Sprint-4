package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StudentsGroupTest{
    val students = listOf(
        Student(firstName = "Сергей", lastName = "Иванов", averageRate = 4.1),
        Student(firstName = "Виктор", lastName = "Степанов", averageRate = 4.2),
        Student(firstName = "Леонид", lastName = "Семенов", averageRate = 4.3),
    )

    @Test
    fun filterByPredicateTest(){
        val studentsGroup = StudentsGroup()
        studentsGroup.students = students
        var filtered = studentsGroup.filterByPredicate { it.firstName.equals("Сергей") }.toList()
        assertEquals(filtered, listOf(students[0]))
        filtered = studentsGroup.filterByPredicate { it.lastName.equals("Степанов") }.toList()
        assertEquals(filtered, listOf(students[1]))
        filtered = studentsGroup.filterByPredicate { it.averageRate.equals(4.3) }.toList()
        assertEquals(filtered, listOf(students[2]))
    }
}
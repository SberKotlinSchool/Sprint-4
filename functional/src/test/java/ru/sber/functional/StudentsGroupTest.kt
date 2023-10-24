package ru.sber.functional

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class StudentsGroupTest {

    private val studentsGroup = StudentsGroup()

    @Test
    fun filterByPredicate() {
        studentsGroup.initializeProperty()

        var resultList = studentsGroup.filterByPredicate { student -> student.firstName.equals("kirill") }

        assertEquals(1, resultList.size)
        assertEquals("kirill", resultList.get(0).firstName)
        assertEquals("timofeev", resultList.get(0).lastName)
        assertEquals(20.0, resultList.get(0).averageRate)

        resultList = studentsGroup.filterByPredicate { student -> student.firstName.equals("andrey") }

        assertEquals(1, resultList.size)
        assertEquals("andrey", resultList.get(0).firstName)
        assertEquals("timofeev", resultList.get(0).lastName)
        assertEquals(10.0, resultList.get(0).averageRate)


        resultList = studentsGroup.filterByPredicate { student -> student.firstName.equals("who") }

        assertEquals(0, resultList.size)
    }


}
package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class StudentsGroupTest {
    val studentsGroup = StudentsGroup()

    @Test
    fun filterByPredicateTest() {
        val student1 = Student(
            "Иван", "Иванов", "Иванович", 26, 4.0,
            "Город", "Специализация", null
        )
        val student2 = Student(
            "Антон", "Антонов", "Антонович", 30, 7.8,
            "Город", "Специализация", null
        )
        val student3 = Student(
            "Виктория", "Викторова", "Викторьевна", 15, 9.6,
            "Город", "Специализация", null
        )
        val student4 = Student(
            "Екатерина", "Катина", "Екатерьневна", 20, 6.4,
            "Город", "Специализация", null
        )
        val student5 = Student(
            "Дмитрий", "Дмитров", "Дмитриевич", 40, 3.2,
            "Город", "Специализация", null
        )

        studentsGroup.students = listOf(student1, student2, student3, student4, student5)
        studentsGroup.filterByPredicate { student: Student -> student.age >= 30 }
        assertEquals(listOf(student2,student5),studentsGroup.students)
        studentsGroup.filterByPredicate { student: Student -> student.averageRate > 5 }
        assertEquals(listOf(student2),studentsGroup.students)

    }
}
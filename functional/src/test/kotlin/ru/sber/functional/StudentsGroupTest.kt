package ru.sber.functional

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StudentsGroupTest {
    private val gson = Gson()
    private val studentsGroup = StudentsGroup()

    @Test
    fun testStudentFilterByPredicate() {
        val student1 = getClientFromJson("/student1.json")
        val student2 = getClientFromJson("/student2.json")
        val student3 = getClientFromJson("/student3.json")
        val student4 = getClientFromJson("/student4.json")
        val student5 = getClientFromJson("/student5.json")
        studentsGroup.students = listOf(student1, student2, student3, student4, student5)

        assertEquals(3, studentsGroup.filterByPredicate { it.age <= 21 }.size)
        assertEquals(2, studentsGroup.filterByPredicate { it.averageRate == 4.0 }.size)
    }

    private fun getClientFromJson(fileName: String): Student = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Student::class.java) }
        ?: throw Exception("Что-то пошло не так))")


}
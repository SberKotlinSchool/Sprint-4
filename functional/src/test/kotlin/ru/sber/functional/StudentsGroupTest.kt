package ru.sber.functional

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class StudentsGroupTest {
    private lateinit var gson: Gson
    private lateinit var studentsGroup: StudentsGroup

    @BeforeEach
    fun setUp() {
        gson = Gson()
        studentsGroup = StudentsGroup().apply { students = getStudentsFromJson("/students.json") }
    }

    @Test
    fun `filterByPredicate returns students with name consisting of 3 letters`() {
        val students = studentsGroup.filterByPredicate { student -> student.firstName.length == 3 }
        assertEquals(2, students.size)
    }

    @Test
    fun `filterByPredicate returns students with an average rating of more 4`() {
        val students = studentsGroup.filterByPredicate { student -> student.averageRate > 4.0 }
        assertEquals(3, students.size)
    }

    private fun getStudentsFromJson(fileName: String): List<Student> = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Array<Student>::class.java).asList() }
        ?: throw Exception("Что-то пошло не так!")
}
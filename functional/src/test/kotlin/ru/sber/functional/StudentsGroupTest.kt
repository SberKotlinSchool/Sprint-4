package ru.sber.functional

import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

internal class StudentsGroupTest {

    @Test
    fun `filter by predicate should proceed correctly`() {
        // given
        val firstStudent = Student("Имян", "Фамильевич-Первый", averageRate = 5.0)
        val secondStudent = Student("Имян", "Фамильевич-Второй", averageRate = 4.0)
        val thirdStudent = Student("Имян", "Фамильевич-Третий", averageRate = 3.0)
        val group = StudentsGroup().also { it.students = listOf(firstStudent, secondStudent, thirdStudent) }

        // when
        group.filterByPredicate { it.averageRate >= 4.0 }

        // then
        assertContentEquals(group.students, listOf(firstStudent, secondStudent))
    }

}
package functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import ru.sber.functional.Student
import ru.sber.functional.StudentsGroup

class StudentsGroupTest {
    @Test
    fun `filter by predicate`() {
        val studentsGroup = StudentsGroup()
        studentsGroup.students = listOf(
            Student(age = 40, firstName = "Test1", lastName = "Last1", averageRate = 10.5),
            Student(age = 40, firstName = "Test1", lastName = "Last1", averageRate = 40.5),
        )
        val pred: (Student) -> Boolean = { it.age > 30 }
        val pred2: (Student) -> Boolean = { it.averageRate > 30 }
        val old: List<Student> = studentsGroup.filterByPredicate(pred)
        val old2: List<Student> = studentsGroup.filterByPredicate(pred, pred2)
        assertEquals(2, old.size)
        assertEquals(1, old2.size)
    }
}

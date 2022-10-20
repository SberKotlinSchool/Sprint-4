import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import ru.sber.functional.Student
import ru.sber.functional.StudentsGroup

internal class StudentsGroupTest {

    lateinit var studentsGroup: StudentsGroup
    lateinit var studentList: List<Student>

    lateinit var noAverageRate: Student
    lateinit var valerka: Student
    lateinit var noLastName: Student
    lateinit var noFirstName: Student
    lateinit var mashqa: Student

    @BeforeEach
    fun setUp() {

        valerka = Student(firstName = "Maxim", lastName = "Brother", averageRate = 2.0)
        noAverageRate = Student(firstName = "Ivan", lastName = "Brother", averageRate = Double.NaN)
        noLastName = Student(firstName = "Drew", lastName = "", averageRate = 2.0)
        noFirstName = Student(firstName = "", lastName = "Brother", averageRate = 3.0)
        mashqa = Student(firstName = "Mari", lastName = "Starikova", averageRate = 100.0)

        studentList = listOf(
            valerka,
            noLastName,
            noFirstName,
            noAverageRate,
            mashqa
        )

        studentsGroup = StudentsGroup()
    }

    @AfterEach
    fun tearDown() {
        studentList = emptyList()
    }

    @Test
    fun filterByPredicateTest() {
        // should hide the setter
        studentsGroup.students = studentList

        val filteredList = studentsGroup.filterByPredicate { student: Student -> student.lastName == "Brother" }

        assertEquals(3, filteredList.size)

        assert(filteredList.contains(noFirstName))
        assert(filteredList.contains(noAverageRate))
        assert(filteredList.contains(valerka))
    }

    @Test
    fun initTest() {
        studentsGroup.init(studentList)

        val studentList = studentsGroup.students

        assertEquals(2, studentList.size)

        assert(studentList.contains(mashqa))
        assert(studentList.contains(valerka))

        assert(!studentList.contains(noAverageRate))
        assert(!studentList.contains(noLastName))
        assert(!studentList.contains(noFirstName))
    }
}
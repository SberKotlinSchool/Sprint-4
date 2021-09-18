package ru.sber.functional

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class StudentsGroupTest {

    private var studentGroup = StudentsGroup()

    @BeforeEach
    fun init(){
        studentGroup.init(listOf(
                Student("Oleg",
                        "Olegov",
                        averageRate = 20.1),
                Student("Baron",
                        "Baronov",
                        averageRate = 60.0),
                Student("Andrey",
                        "Andreev",
                        averageRate = 34.5)
        ))
    }


    @Test
    fun filterByPredicateWithAverageRate() {
        val result = ArrayList<Student>()
        studentGroup.filterByPredicate{ it.averageRate > 50.0 }.forEach { result.add(it) }

        assertTrue(result[0] == Student("Baron",
                "Baronov",
                averageRate = 60.0))
    }

    @Test
    fun filterByPredicateWithFirstName() {
        val result = ArrayList<Student>()
        studentGroup.filterByPredicate { it.firstName == "Andrey" }.forEach { result.add(it) }

        assertTrue(result[0] == Student("Andrey",
                "Andreev",
                averageRate = 34.5))
    }

}

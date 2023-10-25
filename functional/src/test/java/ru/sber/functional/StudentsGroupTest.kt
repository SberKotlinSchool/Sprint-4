package ru.sber.functional

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.util.stream.IntStream
import kotlin.random.Random

class StudentsGroupTest {
    val STUDENT_QUANTITY = 30
    var studentGroup = StudentsGroup()
    @BeforeEach
    fun setUp(){
        studentGroup.students =
            List(STUDENT_QUANTITY,
                { Student( firstName = "First$it",
                    lastName = "LastName".substring(0,3 + it%3),
                    age = 20 + Random.nextInt(5),
                    averageRate = 4.0 + if ( it%2==0) Random.nextDouble(1.0)else -Random.nextDouble(1.0),
                    city = "city${it%5}",
                    prevEducation = if ( it%2==0) "MSU" else "NSU"
                )
                }
            )
    }

    @Test
    fun `test LastName length eq 3`(){
        assertEquals( 10, studentGroup.filterByPredicate { it.lastName.length == 3 }.size)
    }
    @Test
    fun `test city eq city1`(){
        assertEquals( 6, studentGroup.filterByPredicate { it.city == "city1" }.size)
    }

    @Test
    fun `test averageRate gt 4`(){
        assertEquals( 15, studentGroup.filterByPredicate { it.averageRate >= 4.0 }.size)
    }
}
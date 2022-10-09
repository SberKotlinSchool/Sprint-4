package ru.sber.functional

import io.mockk.InternalPlatformDsl.toStr
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import kotlin.random.Random

internal class StudentsGroupTest {


    lateinit var group : StudentsGroup

    @Test
    fun `filterByPredicate ivan haters`() {
        val ivanHaters = group
            .filterByPredicate { it.firstName != "Иван" }
        assertTrue(!ivanHaters.any { it.firstName == "Иван" })
    }


    @Test
    fun `filterByPredicate only a mark students`() {
        val aMarkStudents = group
            .filterByPredicate { it.averageRate > 4.8 }
        assertTrue(!aMarkStudents.any { it.averageRate <= 4.8 })
    }

    @BeforeEach
    internal fun setUp() {
        group = StudentsGroup()
        group.students = mutableListOf()

        repeat(10) {
            (group.students as MutableList<Student>).add(
                Student(
                    listOf("Иван", "Петр", "Алексей", "Игорь").random(),
                    listOf("Григорьев", "Апполонов", "Весельчаков", "Густавьев")
                        .asSequence().shuffled().take(2).toList().run {
                            "${this[0]}-${this[1]}"
                        },
                    listOf("Иваныч", "Сергеевич", "Илонович").random(),
                    Random.nextInt(18, 24),
                    Random.nextDouble() % 2 + 3,
                    "Moscow",
                    "Kotlin developer",
                    null
                )
            )
        }
    }
}
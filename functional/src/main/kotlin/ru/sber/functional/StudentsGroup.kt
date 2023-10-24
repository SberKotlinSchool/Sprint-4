package ru.sber.functional

import kotlin.random.Random

class StudentsGroup {

    var students: List<Student>

    init {
        students = (1..10).map {
            Student(firstName = "Student$it",
                    lastName = "Student$it",
                    middleName = "MiddleName",
                    age = Random.nextInt(16, 22),
                    averageRate = it.toDouble(),
                    city = "City",
                    specialization = "Specialization",
                    prevEducation = "PrevEducation")
        }
    }
    fun filterByPredicate(function: (s: Student) -> Boolean) {
        students = students.filter { function(it) }
    }
}

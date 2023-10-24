package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {

        val filteredStudents = mutableListOf<Student>()

        for (student in students) {
            if (predicate(student)) {
                filteredStudents.add(student)
            }
        }
        return filteredStudents
    }

    fun initializeProperty() {
        students =
            listOf(
                Student(
                    "kirill",
                    "timofeev",
                    "none",
                    0,
                    20.0,
                    "none",
                    "Специализация отсутствует",
                    null,
                ),
                Student(
                    "andrey",
                    "timofeev",
                    "none",
                    0,
                    10.0,
                    "none",
                    "none",
                    null,
                ),
            )
    }
}

fun main() {
    val studentsGroup = StudentsGroup()

    studentsGroup.initializeProperty()

    val resultList = studentsGroup.filterByPredicate { student -> student.middleName.equals("Andreevich") }

    resultList.forEach { println(it) }
}

package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student> = listOf(
        Student(
            firstName = "Иван",
            lastName = "Иванов",
            averageRate = 4.0
        ),
        Student(
            firstName = "Петр",
            lastName = "Петров",
            averageRate = 4.25
        ),
        Student(
            firstName = "Сергей",
            lastName = "Сергеев",
            averageRate = 4.5
        ),
        Student(
            firstName = "Василий",
            lastName = "Васильев",
            averageRate = 4.75
        ),
        Student(
            firstName = "Константин",
            lastName = "Константинов",
            averageRate = 5.0
        )
    )

    fun filterByPredicate(predicate: (Student) -> Boolean) : List<Student> {
        students.filter(predicate)
    }
}

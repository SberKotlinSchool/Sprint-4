package ru.sber.functional

class StudentsGroup(
    var students: List<Student> = listOf(
        Student(
            firstName = "firstName1",
            lastName = "lastName1",
            averageRate = 0.0
        ),
        Student(
            firstName = "firstName2",
            lastName = "lastName2",
            averageRate = 0.1
        ),
        Student(
            firstName = "firstName3",
            lastName = "lastName3",
            averageRate = 0.2
        ),
        Student(
            firstName = "firstName4",
            lastName = "lastName4",
            averageRate = 0.3
        )
    )
) {

    fun filterByPredicate(rule: (Student) -> Boolean) {
        students = students.filter(rule)
    }
}


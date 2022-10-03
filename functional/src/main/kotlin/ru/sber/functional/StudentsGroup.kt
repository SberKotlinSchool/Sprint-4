package ru.sber.functional

class StudentsGroup {

    private var students: List<Student> = listOf(
        Student(firstName = "Даниил", lastName = "Леонов", averageRate = 5.0),
        Student(firstName = "Иван", lastName = "Романов", averageRate = 4.9),
        Student(firstName = "Петр", lastName = "Романов", averageRate = 4.8)
    )

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        return students.filter(predicate)
    }

}

package ru.sber.functional

class StudentsGroup {

    private val students: List<Student> = listOf(
        Student(firstName = "Иван", lastName = "Иванов", averageRate = 8.4),
        Student(firstName = "Игорь", lastName = "Петров", averageRate = 6.4),
        Student(firstName = "Петр", lastName = "Сидоров", averageRate = 9.4)
    )

    fun filterByPredicate(predicate: (Student) -> Boolean) = students.filter(predicate)
}

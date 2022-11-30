package ru.sber.functional

class StudentsGroup {

    var students: List<Student>
    init {
        students = listOf(
            Student(firstName = "Иван",lastName = "Иванов", averageRate = 4.0),
            Student(firstName = "Петр",lastName = "Петров", averageRate = 4.1),
            Student(firstName = "Сидор",lastName = "Сидоров", averageRate = 4.2))
    }
    fun filterByPredicate(filter: (Student) -> Boolean) = students.filter(filter)
}

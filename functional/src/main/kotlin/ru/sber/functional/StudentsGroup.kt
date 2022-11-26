package ru.sber.functional

class StudentsGroup {

     var students: List<Student> = listOf(
        Student(firstName = "Иван",lastName = "Иванов", averageRate = 4.7),
        Student(firstName = "Петр",lastName = "Петров", averageRate = 4.2),
        Student(firstName = "Семен",lastName = "Семенов", averageRate = 4.5)
    )

    fun filterByPredicate(predicate: (Student) -> Boolean) {
        students = students.filter(predicate)
    }
}

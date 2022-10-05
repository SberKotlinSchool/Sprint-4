package ru.sber.functional

class StudentsGroup {

    val students : List<Student> = listOf(
        Student(lastName = "Иванов", firstName = "Иван", averageRate = 3.5),
        Student(lastName = "Петров", firstName = "Петр", averageRate = 5.0),
        Student(lastName = "Сидоров", firstName = "Сидор", middleName = "Сидорович" , averageRate = 1.8),
    )

    inline infix fun filterByPredicate(condition : (Student) -> Boolean) = students.filter(condition)
}

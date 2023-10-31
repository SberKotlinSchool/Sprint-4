package ru.sber.functional

class StudentsGroup {

    var students: List<Student> = listOf(
        Student("Иван","Иванов", averageRate = 3.7),
        Student("Петр","Петров", averageRate = 4.1),
        Student("Сидр","Сидоров", averageRate = 3.9)
    )
    fun filterByPredicate(filterFunc: (Student) -> Boolean) {
        students = students.filter(filterFunc)    }
}
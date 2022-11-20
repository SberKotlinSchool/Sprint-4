package ru.sber.functional

class StudentsGroup {

    var students: List<Student>

    init {
        students = listOf(
            Student("Иванов", "Иван", averageRate = 5.0),
            Student("Петров", "Петр", averageRate = 4.7),
            Student("Сидоров", "Сидр", averageRate = 4.5),
        )
    }

    fun filterByPredicate(predFun: (Student) -> Boolean) {
        students = students.filter(predFun)
    }
}

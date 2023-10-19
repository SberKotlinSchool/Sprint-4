package ru.sber.functional

class StudentsGroup{

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean) = students.filter(predicate)

    init {
        init()
    }

    fun init() : Unit {
        students = listOf(
            Student("Иван", "Иванов", averageRate = 5.0),
            Student("Петр", "Петров", averageRate = 4.5),
            Student("Иван", "Васильев", averageRate = 4.0),
            Student("Ивашка", "Сидоров", averageRate = 3.5),
            Student("Павел", "Павлов", averageRate = 3.0),
            Student("Егор", "Егоров", averageRate = 2.5),
            Student("Максим", "Максимов", averageRate = 2.0),
        )
    }
}

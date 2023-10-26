package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        return students.filter(predicate)
    }

    init {
        students = listOf(
            Student("Ivan", "Ivanov", "Ivanovich", 20, 4.5, "Undefined", "Undefined", "Undefined"),
            Student("Petr", "Petrov", "Petrovich", 19, 4.8, "Undefined", "Undefined", "Undefined"),
            Student("Semen", "Semenov", "Semenovich", 20, 4.5, "Undefined", "Undefined", "Undefined"),
        )
    }
}

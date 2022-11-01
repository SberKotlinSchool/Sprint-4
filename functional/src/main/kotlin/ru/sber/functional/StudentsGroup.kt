package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    init {
        students = mutableListOf(
            Student("Василий", "Васильев", 4.5),
            Student("Пётр", "Петров", 5.0),
            Student("Иван", "Иванов", 4.2),
            Student("Юлия", "Иванова", 4.8)
        )
    }

    fun filterByPredicate(predicate: (Student) -> Boolean) =
        students.filter(predicate)
}

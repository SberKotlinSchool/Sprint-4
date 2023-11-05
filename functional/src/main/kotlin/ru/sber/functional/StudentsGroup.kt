package ru.sber.functional

class StudentsGroup {

    private var students: List<Student> = listOf(
            Student("Иван", "Иванов", averageRate = 4.5),
            Student("Петр", "Петров", averageRate = 3.7),
            Student("Сергей", "Сергеев", averageRate = 4.8)
    )

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        return students.filter(predicate)
    }
}

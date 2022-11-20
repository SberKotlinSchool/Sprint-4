package ru.sber.functional

class StudentsGroup {

    private var students: List<Student> = listOf(
        Student(firstName = "Дмитрий", lastName = "Иванов", averageRate = 1.0),
        Student(firstName = "Антон", lastName = "Смирнов", averageRate = 2.0),
        Student(firstName = "Лука", lastName = "Соболев", averageRate = 3.0),
        Student(firstName = "Ефросинья", lastName = "Карибидиз", averageRate = 4.0),
        Student(firstName = "Панкрат", lastName = "Черный", averageRate = 5.0)
    )

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> { return students.filter(predicate) }
}
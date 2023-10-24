package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> = students.filter(predicate)
}

fun main() {
    val group = StudentsGroup().apply {
        students = listOf(
            Student(firstName = "Кирилл", lastName = "Иванов", averageRate = 4.4),
            Student(firstName = "Иван", lastName = "Сергеев", averageRate = 3.5),
            Student(firstName = "Анна", lastName = "Петрова", city = "Москва", averageRate = 4.8),
            Student(firstName = "Алексей", lastName = "Курочкин", averageRate = 3.1)
        )
    }

    group.filterByPredicate {
        it.averageRate > 4
    }
        .joinToString("\n")
        .also { println(it) }
}

package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean) = students.filter { predicate.invoke(it) }
}

fun main() {
    StudentsGroup().apply {
        students = listOf(
            Student(firstName = "Ivan", "Bolvan", averageRate = 1.0),
            Student(firstName = "Petr", "Minivan", averageRate = 2.0),
            Student(firstName = "Igor", "Padovan", averageRate = 3.0),
            Student(firstName = "Kate", "Karavan", averageRate = 4.5),
            Student(firstName = "Hate", "Me", averageRate = 5.0),
        )
    }
        .filterByPredicate { it.averageRate > 3 }
        .forEach { println(it) }
}

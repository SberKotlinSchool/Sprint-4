package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    init {
        students = listOf(
            Student(firstName = "Иван", lastName = "Иванов", averageRate = 40.0),
            Student(firstName = "Иван",lastName = "Петров",averageRate = 70.0),
            Student(firstName = "Иван",lastName = "Сидоров",averageRate = 80.0),
            Student(firstName = "Лев",lastName = "Гумилёв",averageRate = 50.0),
            Student(firstName = "Александр",lastName = "Пушкин",averageRate = 100.0),
            Student(firstName = "Лев",lastName = "Толстой",averageRate = 98.1),
            Student(firstName = "Фёдр",lastName = "Достоевский",averageRate = 98.2)
        )
    }

    fun filterByPredicate(vararg predicates: (Student) -> Boolean ) : List<Student> {
        var result = students
        predicates.forEach { result = result.filter(it) }
        return result
    }


    fun goodAverageRate(): (Student) -> Boolean = { it.averageRate > 69 }
    fun badAverageRate(): (Student) -> Boolean = { it.averageRate < 19 }
    fun firstNameIsIvan(): (Student) -> Boolean = { it.firstName == "Иван" }
    fun lastNameIsSidorov(): (Student) -> Boolean = { it.lastName == "Сидоров" }
    fun firstNameIsLevAndGoodAverageRate(): (Student) -> Boolean = {
        it.firstName == "Лев" && it.averageRate > 80
    }
}

fun main() {

    val studentsGroup = StudentsGroup()

    val result = studentsGroup
        .filterByPredicate(
            //studentsGroup.firstNameIsIvan(),
            //studentsGroup.badAverageRate(),
            studentsGroup.goodAverageRate(),
            //studentsGroup.lastNameIsSidorov(),
            //studentsGroup.firstNameIsLevAndGoodAverageRate()
        )

    println("${result.size}/${studentsGroup.students.size}")
    for (student in result) {
        println("${student.firstName} " +
                "${student.lastName} " +
                "${student.averageRate}")
    }
}

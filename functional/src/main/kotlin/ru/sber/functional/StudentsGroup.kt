package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        return students.filter(predicate)

    }
}

fun main() {
    val studentsGroup = StudentsGroup()
    studentsGroup.students = listOf(
        Student("Kolya", "Ivanov", "Petrovich", 27, 8.8, "Saint-Petersburg", "Developer", "None"),
        Student("Vasya", "Ivanov", "Alekseevich", 27, 3.8, "Saint-Petersburg", "Developer", "None")
    )
    val filterByPredicate = studentsGroup.filterByPredicate { student -> student.firstName == "Kolya" }

    println(filterByPredicate)

}

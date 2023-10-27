package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        return students.filter(predicate)
    }
}

// testing
fun main() {
    val sg = StudentsGroup()

    sg.students = listOf(
        Student("Fedor", "Filippov", "E", 21, 7.0, "M", "NA", "NA"),
        Student("Michael", "Jordan", "B", 22, 8.0, "W", "NA", "NA"),
        Student("Michael", "Myers", "K", 31, 3.0, "M", "NA", "NA"),
        )

    println(sg.filterByPredicate { student -> student.firstName == "Michael" })
    println(sg.filterByPredicate { student -> student.age > 30 })
}

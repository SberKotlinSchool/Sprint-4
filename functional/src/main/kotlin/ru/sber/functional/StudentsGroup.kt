package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(function: (Student) -> Boolean) {
        students = students.filter(function)
    }
}

fun main() {
    val group = StudentsGroup()
    group.students = listOf(
        Student(firstName = "Онегин", lastName = "Евгений", middleName = "не указано", age = 18,
            averageRate = 3.4, city = "не указано", specialization = "физика", prevEducation = null),
        Student(firstName = "Девушкин", lastName = "Макар", middleName = "не указано", age = 27,
            averageRate = 4.2, city = "не указано", specialization = "архитектура", prevEducation = null),
        Student(firstName = "Добросёлова", lastName = "Варвара", middleName = "не указано", age = 19,
            averageRate = 4.8, city = "не указано", specialization = "литература", prevEducation = null),
        Student(firstName = "Швабрин", lastName = "Алексей", middleName = "не указано", age = 23,
            averageRate = 5.0, city = "не указано", specialization = "не указано", prevEducation = null),
        Student(firstName = "Гринёв", lastName = "Пётр", middleName = "не указано", age = 17,
            averageRate = 5.0, city = "не указано", specialization = "агрономия", prevEducation = null)
    )

    println(group.students)
    group.filterByPredicate { student: Student -> student.averageRate > 4 }
    println(group.students)
    group.filterByPredicate { student: Student -> student.age > 25 }
    println(group.students)
}

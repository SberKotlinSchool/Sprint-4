package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean) = students.filter(predicate)
}

fun main() {
    val studentsGroup = StudentsGroup()
    studentsGroup.students = listOf(
        Student("Павел", "Чистяков", "Андреевич", 22, 5.0, "Рязань",
            "Программная инженерия", null),
        Student("Алексей", "Ронжин", "Владимирович", 22, 4.5, "Рязань",
            "Программная инженерия", null),
        Student("Юрий", "Шайдуллин", "Сергеевич", 25, 4.2, "Рязань",
            "Программная инженерия", null)
    )
    println(studentsGroup.filterByPredicate { it.averageRate >= 4.5 })
}
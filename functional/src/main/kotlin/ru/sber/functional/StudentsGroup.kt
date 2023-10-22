package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    init {
        students = mutableListOf(
            Student("Ann", "Lee", 4.5),
            Student("Ben", "Bond", 5.0)
        )
    }

    fun filterByPredicate(predicate: (Student) -> Boolean) =
        students.filter(predicate)

}

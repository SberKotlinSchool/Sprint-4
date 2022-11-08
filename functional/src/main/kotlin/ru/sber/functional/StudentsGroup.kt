package ru.sber.functional

class StudentsGroup {

    var students: List<Student> = listOf(
        Student("Александр", "Алехин", 3.0),
        Student("Андрей", "Смирнов", 4.5),
        Student("Иван", "Васильев", 5.0)
    )

    fun filterByPredicate(predicate: (Student) -> Boolean) {
        students = students.filter(predicate)
    }
}

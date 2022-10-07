package ru.sber.functional

class StudentsGroup {

    var students: List<Student> = Students().generate(50)

    fun filterByPredicate(predicate: (Student) -> Boolean) =
        students.filter(predicate).toList()
}

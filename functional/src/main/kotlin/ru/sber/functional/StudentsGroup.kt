package ru.sber.functional

class StudentsGroup(private val students: List<Student>) {

    fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        return students.filter(predicate)
    }
}

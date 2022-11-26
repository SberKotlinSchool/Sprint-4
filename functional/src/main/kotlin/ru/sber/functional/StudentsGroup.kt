package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>


    fun filterByPredicate(action: (Student) -> Boolean) : List<Student> {
        return students.filter ( action ).toList()
    }
}

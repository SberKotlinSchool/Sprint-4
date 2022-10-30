package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(vararg predicates: (s: Student) -> Boolean) =
        students.filter { s ->
            predicates.filter {
                it(s)
            }.size == predicates.size
        }
}

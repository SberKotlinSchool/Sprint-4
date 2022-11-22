package ru.sber.functional

class StudentsGroup{

    lateinit var students: List<Student>

    init {
        init()
    }

    fun init() : Unit {
        students = listOf(
            Student("Va", "vo", averageRate = 4.5),
            Student("Vas", "vos", averageRate = 3.5),
            Student("Vaq", "voq", averageRate = 2.5),
            Student("Vta", "vot", averageRate = 1.5)
        )
    }

    fun filterByPredicate(predicate: (Student) -> Boolean) = students.filter(predicate)
}

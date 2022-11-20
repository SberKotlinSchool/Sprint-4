package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    init {
        students = listOf(Student(firstName = "Майк", lastName = "Вазовский", averageRate = 5.0),
            Student(firstName = "Джеймс", lastName = "Салливан", averageRate = 5.0),
            Student(firstName = "Рэнди", lastName = "Боггс ", averageRate = 4.0),
            Student(firstName = "Тэрри", lastName = "Перри ", averageRate = 3.0),
            Student(firstName = "Дон", lastName = "Карлтон ", averageRate = 4.2),
            Student(firstName = "Скотт", lastName = "Склизли ", averageRate = 4.5))
    }

    fun filterByPredicate(predicate: (Student)->Boolean)  = students.filter(predicate)
}

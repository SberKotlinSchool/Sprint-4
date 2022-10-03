package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate : (Student) -> Boolean) = students.filter(predicate)

    init {
        students = listOf(Student(firstName = "Василий", lastName = "Чапаев", averageRate = 3.0),
            Student(firstName = "Петр", lastName = "Исаев", averageRate = 4.0),
            Student(firstName = "Дмитрий", lastName = "Фурманов", averageRate = 5.0),
            Student(firstName = "Анна", lastName = "Стешенко", averageRate = 4.4))
    }
}

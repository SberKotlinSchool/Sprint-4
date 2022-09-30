package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(predicate: (Student) -> Boolean) = students.filter(predicate)

    init {
        students = listOf(Student(firstName = "Иван", lastName = "Иванов", averageRate = 5.0),
            Student(firstName = "Инна", lastName = "Антонова", averageRate = 4.5),
            Student(firstName = "Олег", lastName = "Ефимцев", averageRate = 3.0))
    }

}

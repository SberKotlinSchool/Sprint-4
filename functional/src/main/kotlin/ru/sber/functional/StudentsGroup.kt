package ru.sber.functional

class StudentsGroup {

    private val students: List<Student> = listOf(
        Student(firstName = "Тихомир", lastName = "Ярополкович", averageRate = 5.1),
        Student(firstName = "Светозар", lastName = "Святославич", averageRate = 5.2),
        Student(firstName = "Ратибор", lastName = "Всеволодович", averageRate = 5.3)
    )

    fun filterByPredicate(predicate: (Student) -> Boolean): Collection<Student> = students.filter(predicate)
}

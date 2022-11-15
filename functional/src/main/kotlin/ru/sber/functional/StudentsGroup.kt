package ru.sber.functional

class StudentsGroup {

    private var students: List<Student> = listOf(
        Student(
            firstName = "Иван",
            lastName = "Петров",
            averageRate = 33.5
        ),
        Student(
            firstName = "Сергей",
            lastName = "Васильев",
            averageRate = 15.7
        ),
        Student(
            firstName = "Дмитрий",
            lastName = "Краснов",
            averageRate = 5.4
        ),
        Student(
            firstName = "Вячеслав",
            lastName = "Голубев",
            averageRate = 23.7,
            age = 18,
            city = "Москва"
        ),
    )

    fun filterByPredicate(params: ((Student) -> Boolean)): List<Student> {
        return students.filter(params)
    }
}

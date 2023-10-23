package ru.sber.functional

class StudentsGroup(
    val students: List<Student> = listOf(
        createStudent("Кирилл", "Мишин", 4.0),
        createStudent("Георгий", "Агафонов", 4.1),
        createStudent("Виссарион", "Аксенов", 4.2),
        createStudent("Иван", "Иванов", 4.3),
    )
) {
    inline fun filterByPredicate(predicate: (Student) -> Boolean): List<Student> {
        return students.filter(predicate)
    }
}

private fun createStudent(
    firstName: String,
    lastName: String,
    averageRate: Double
): Student = Student(
    firstName = firstName,
    lastName = lastName,
    averageRate = averageRate,
    middleName = "Отчество отсутствует",
    age = -1,
    city = "Город отсутствует",
    specialization = "Специализация отсутствует",
    prevEducation = null
)

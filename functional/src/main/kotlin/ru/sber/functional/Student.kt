package ru.sber.functional

// у которых обязательно указывается: фамилия, имя и средний бал
data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество отсутствует",
    val age: Int = 18,
    val averageRate: Double,
    val city: String = "Город отсутствует",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "Пред. образование отсутствует",
)

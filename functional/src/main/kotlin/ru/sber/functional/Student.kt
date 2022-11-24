package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество отсутствует",
    val age: Int  = 0,
    val averageRate: Double,
    val city: String = "Город отсутствует",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "Предыдущее образование отсутствует",
)

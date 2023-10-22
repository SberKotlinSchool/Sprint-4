package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val averageRate: Double,
    val middleName: String = "",
    val age: Int = 18,
    val city: String = "London",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "average",
)

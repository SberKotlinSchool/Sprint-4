package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "",
    val age: Int = 18,
    val averageRate: Double,
    val city: String = "Russia",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "Специализация отсутствует",
)

package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String? = null,
    val age: Int? = null,
    val averageRate: Double,
    val city: String? = null,
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "Специализация отсутствует",
)

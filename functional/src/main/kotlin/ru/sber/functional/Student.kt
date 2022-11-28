package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Неизвестно",
    val age: Int = 18,
    val averageRate: Double,
    val city: String = "Moscow",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = null,
)

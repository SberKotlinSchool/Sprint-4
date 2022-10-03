package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Нет данных",
    val age: Int = 999,
    val averageRate: Double,
    val city: String = "Нет данных",
    val specialization: String = "Нет данных",
    val prevEducation: String? = "Нет данных",
)

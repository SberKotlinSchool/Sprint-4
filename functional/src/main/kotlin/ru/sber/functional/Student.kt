package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val averageRate: Double,
    val middleName: String = "Неизвестно",
    val age: Int? = null,
    val city: String = "Город не известен",
    val specialization: String = "Специализация не задана",
    val prevEducation: String? = null,
)

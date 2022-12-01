package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Не указано",
    val age: Int = -1,
    val averageRate: Double,
    val city: String = "Не указано",
    val specialization: String = "Не указано",
    val prevEducation: String? = null,
)

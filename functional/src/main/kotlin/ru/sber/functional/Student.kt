package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val age: Int,
    val averageRate: Double,
    val city: String = "Не указан",
    val specialization: String = "Отсутствует",
    val prevEducation: String? = "Отсутствует",
)

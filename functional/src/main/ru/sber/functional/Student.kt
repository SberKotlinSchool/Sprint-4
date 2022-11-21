package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество отсутствует",
    val age: Int = 20,
    val averageRate: Double,
    val city: String = "city",
    val specialization: String ="Специализация отсутствует",
    val prevEducation: String? = null,
)

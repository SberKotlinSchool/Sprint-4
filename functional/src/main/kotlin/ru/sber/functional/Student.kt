package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "NA",
    val age: Int = 19,
    val averageRate: Double = 5.0,
    val city: String = "NA",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "NA",
)

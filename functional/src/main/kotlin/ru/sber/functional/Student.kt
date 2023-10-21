package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "не указано отчество",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "не указан город",
    val specialization: String = "не указана специальность",
    val prevEducation: String? = null,
)

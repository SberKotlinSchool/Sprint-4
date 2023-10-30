package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Н/Д",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "Н/Д",
    val specialization: String = "Н/Д",
    val prevEducation: String? = "Н/Д",
)

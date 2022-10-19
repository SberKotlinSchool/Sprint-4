package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "No middleName is specified",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "No city is specified",
    val specialization: String = "No specialization is specified",
    val prevEducation: String? = "No previous education is specified",
)

package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "stub",
    val age: Int = 0,
    val averageRate: Double,
    val city: String  = "stub",
    val specialization: String  = "stub",
    val prevEducation: String?  = "stub",
)

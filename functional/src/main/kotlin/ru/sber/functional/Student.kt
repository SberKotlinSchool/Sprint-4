package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String = "Unknown",
    val middleName: String = "Unknown",
    val age: Int = 20,
    val averageRate: Double,
    val city: String = "Unknown",
    val specialization: String = "Not defined",
    val prevEducation: String? = "Absent"
)
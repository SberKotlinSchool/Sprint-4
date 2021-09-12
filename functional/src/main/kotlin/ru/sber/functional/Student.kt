package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val age: Int,
    val averageRate: Double,
    val city: String,
    val specialization: String,
    val prevEducation: String?,
)

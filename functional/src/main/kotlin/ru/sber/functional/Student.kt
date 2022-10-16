package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val averageRate: Double,
    val middleName: String? = null,
    val age: Int? = null,
    val city: String? = null,
    val specialization: String? = null,
    val prevEducation: String? = null,
)

package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "No middlename",
    val age: Int = -1,
    val averageRate: Double,
    val city: String = "Homeless",
    val specialization: String = "Useless",
    val prevEducation: String? = null,
)

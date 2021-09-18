package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "No Middle Name",
    val age: Int = 18,
    val averageRate: Double,
    val city: String = "No City",
    val specialization: String = "No Specialization",
    val prevEducation: String? = null
)

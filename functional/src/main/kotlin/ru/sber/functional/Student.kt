package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "",
    val age: Int = -1,
    val averageRate: Double,
    val city: String = "город не указан",
    val specialization: String = "нет специализации",
    val prevEducation: String? = null,
)

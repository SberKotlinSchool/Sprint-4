package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество отсутвует",
    val age: Int = 99,
    val averageRate: Double,
    val city: String = "Город не определён",
    val specialization: String = "Специализация не определена",
    val prevEducation: String? = null,
)

package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество отсутствует",
    val age: Int,
    val averageRate: Double = -1.0,
    val city: String = "Город отсутвует",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = null,
)

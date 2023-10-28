package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество отсутствует",
    val age: Int = -1,
    val averageRate: Double,
    val city: String = "Информация о городе отсутствует",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = null,
)

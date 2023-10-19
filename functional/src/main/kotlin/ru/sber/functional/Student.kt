package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Не помнит свое отчество",
    val age: Int = 18,
    val averageRate: Double,
    val city: String = "Город не указан",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "Без образования",
)

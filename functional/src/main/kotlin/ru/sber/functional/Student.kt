package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Без отчества",
    val age: Int = 22,
    val averageRate: Double,
    val city: String = "Неизвестный город",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = null,
)

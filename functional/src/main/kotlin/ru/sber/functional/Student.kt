package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Нет отчества",
    val age: Int = 20,
    val averageRate: Double,
    val city: String = "Город неизвестен",
    val specialization:  String = "Специализация отсутствует",
    val prevEducation: String? = null,
)

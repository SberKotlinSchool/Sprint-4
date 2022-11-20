package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Данные отсутствуют",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "Данные отсутствуют",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "Данные отсутствуют",
)

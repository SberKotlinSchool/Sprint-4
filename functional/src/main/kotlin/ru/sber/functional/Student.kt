package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val averageRate: Double,
    val middleName: String = "",
    val age: Int = 20,
    val city: String = "Нижний Новгород",
    val specialization: String = "Специализация отсутствует",
    val prevEducation: String? = "Среднее общее"
)

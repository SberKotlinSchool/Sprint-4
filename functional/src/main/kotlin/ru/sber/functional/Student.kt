package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество не указано",
    val age: Int = 19,
    val averageRate: Double,
    val city: String = "Москва",
    val specialization: String = "Информационные технологии",
    val prevEducation: String? = "Нейронные сети",
)

package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String = "Отчество отсутствует",
    val age: Int = 0,
    val averageRate: Double,
    val city: String = "Город не указан",
    val specialization: String = "Специальность не известна",
    val prevEducation: String? = "Информация об образовании отсутствует",
)

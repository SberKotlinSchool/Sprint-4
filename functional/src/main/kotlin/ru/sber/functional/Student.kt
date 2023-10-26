package ru.sber.functional

data class Student(
        val firstName: String,
        val lastName: String,
        val middleName: String = "Безымяно(вич/вна)",
        val age: Int = 18,
        val averageRate: Double,
        val city: String = "Default city",
        val specialization: String = "Программист",
        val prevEducation: String? = "Отсутствует",
)

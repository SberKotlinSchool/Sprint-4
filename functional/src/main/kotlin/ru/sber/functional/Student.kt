package ru.sber.functional

data class Student(
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val age: Int,
    val averageRate: Double,
    val city: String,
    val specialization: String,
    val prevEducation: String?
)
{
    override fun equals(other: Any?): Boolean {
        if(other == null) return false
        if(javaClass != other.javaClass) return false
        other as Student
        return (firstName == other.firstName
                && lastName == other.lastName
                && middleName == other.middleName
                && age == other.age
                && averageRate == other.averageRate
                && city == other.city
                && specialization == other.specialization
                && prevEducation == other.prevEducation)
    }
}

package ru.sber.functional

const val TEENAGER_AGE: Int = 18

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(filter: (Student) -> Boolean) {
        students = students.filter(filter)
    }
}


fun main() {
    val studentsGroup = StudentsGroup()
    studentsGroup.students = getStudentsList()
    studentsGroup.filterByPredicate { student: Student -> student.age > TEENAGER_AGE }

    print(studentsGroup.students)
}

fun getStudentsList(): List<Student> {
    return listOf(
        Student(firstName = "Ivan", lastName = "Kondratov", middleName = "Valentine", age = 19),
        Student(firstName = "Vasya", lastName = "Terzi", middleName = "Andrew", age = 13),
        Student(firstName = "Andrew", lastName = "Mihaylov", middleName = "Yury", age = 14),
        Student(firstName = "Yury", lastName = "Anisimov", middleName = "Igor", age = 21, averageRate = 8.0),
        Student(firstName = "Vasil", lastName = "Kondratenko", middleName = "Anton", age = 11),
        Student(firstName = "Svyatoslav", lastName = "Zavelishko", middleName = "Evgeniy", age = 18, city = "Sochi"),
        Student(firstName = "Mihail", lastName = "Mongolov", middleName = "Igor", age = 18),
        Student(firstName = "Leonard", lastName = "Siforov", middleName = "Sergey", age = 34),
    )
}

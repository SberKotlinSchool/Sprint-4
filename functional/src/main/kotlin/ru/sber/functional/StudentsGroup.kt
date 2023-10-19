package ru.sber.functional

fun main(){

    var studentGroup = StudentsGroup()
    studentGroup.students = listOf( Student("Иван", "Иванов", averageRate = 4.2),
                                       Student("Алексей", "Алексеев", averageRate = 3.7),
                                       Student("Сергей", "Сергеев", averageRate = 4.5),
                                       Student("Степан", "Степанов", averageRate = 4.1) )

    println( studentGroup.filterByPredicate { it.averageRate > 4.0  } )

}
class StudentsGroup {

    lateinit var students: List<Student>
    fun filterByPredicate( filter: ( Student ) -> Boolean ) = students.filter(filter)
}

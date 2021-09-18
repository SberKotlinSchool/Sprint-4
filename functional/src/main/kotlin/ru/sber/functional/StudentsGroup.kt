package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(pred: (Student) -> Boolean): List<Student>{
        return students.filter(pred)
    }
    fun init(students : List<Student>){
        this.students=students
    }
}

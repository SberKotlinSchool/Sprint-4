package ru.sber.functional

class StudentsGroup {

    lateinit var students: List<Student>

    fun filterByPredicate(filter: (Student) -> Boolean): List<Student> {
        fun loop(i: Int, tempList: List<Student>): List<Student> =
             when{
                i >= students.size -> tempList
                filter(students[i]) -> loop(i+1, tempList.plus(students[i]))
                else -> loop(i+1, tempList)
            }
        return loop(0, emptyList())
    }

}

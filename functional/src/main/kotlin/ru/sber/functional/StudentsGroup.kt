package ru.sber.functional

/**
 * Есть группа студентов `ru.sber.functional.StudentsGroup`. Необходимо:
 * - написать функцию, которая будет фильтровать по лямбде-предикату, переданной как аргумент.
 * - проинициализировать этот класс студентами, у которых обязательно указывается: фамилия, имя и средний бал.
 * Все остальные свойства инициализируются значением-заглушкой (пример: "Специализация отсутствует")
 */
class StudentsGroup {

    private var students: List<Student> = listOf(
        Student(firstName = "Sergey", lastName = "Brin", averageRate = 4.2, age = 45),
        Student(firstName = "Herbert", lastName = "Schildt", averageRate = 3.7, age = 60),
        Student(firstName = "Bill", lastName = "Gates", averageRate = 3.2, age = 70),
        Student(firstName = "Elon", lastName = "Musk", averageRate = 2.0, age = 48),
        Student(firstName = "Linus", lastName = "Torvalds", averageRate = 5.0, age = 55),
        Student(firstName = "Dima", lastName = "Rogozin", middleName = "Batutovich", averageRate = 1.0, age = 57),
    )

    fun filterByPredicate(predicate: (Student) -> Boolean) : List<Student> {
        return students.filter(predicate)
    }
}

package ru.sber.functional

class Students {
    var students = listOf<Student>()

    fun generate(n: Int = 10): List<Student> {
        repeat(n) { students += randomStudent() }
        return students
    }

    fun print() {
        students.forEach { println(it) }
    }

    private fun randomStudent() = Student(
        firstName = firstnames.random(),
        lastName = lastnames.random(),
        middleName = "",
        age = (20..25).random(),
        averageRate = ((30..50).random() / 10 + (30..50).random() % 10).toDouble(),
        city = cities.random(),
        specialization = "Отсутствует",
        prevEducation = "Отсутствует",
    )

    companion object {
        val lastnames =
            listOf(
                "Иванов",
                "Смирнов",
                "Кузнецов",
                "Попов",
                "Васильев",
                "Петров",
                "Соколов",
                "Михайлов",
                "Новиков",
                "Фёдоров"
            )
        val firstnames =
            listOf("Александр", "Максим", "Михаил", "Марк", "Иван", "Артем", "Лев", "Дмитрий", "Матвей", "Даниил")
        val cities = listOf(
            "Москва",
            "Санкт-Петербург",
            "Новосибирск",
            "Екатеринбург",
            "НижнийНовгород",
            "Самара",
            "Омск",
            "Казань",
            "Челябинск",
            "Ростов-на-Дону"
        )
    }
}

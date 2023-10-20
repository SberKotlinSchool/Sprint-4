import ru.sber.datetime.getFormattedDateTime
import ru.sber.datetime.getLastInMonthDayWeekList
import ru.sber.datetime.getNumberOfFridayThirteensInYear
import ru.sber.datetime.getZonesWithNonDivisibleByHourOffset
import java.time.LocalDateTime

fun main() {
    // 1) Получить сет часовых поясов, которые используют смещение от UTC не в полных часах.
    println(getZonesWithNonDivisibleByHourOffset())

    // 2) Для заданного года вывести список, каким днем недели был последний день в месяце.
    println(getLastInMonthDayWeekList(2021))
    println(getLastInMonthDayWeekList(2022))
    println(getLastInMonthDayWeekList(2019))

    // 3) Для заданного года вывести количество дней, выпадающих на пятницу 13-ое.
    println(getNumberOfFridayThirteensInYear(2021))
    println(getNumberOfFridayThirteensInYear(2022))
    println(getNumberOfFridayThirteensInYear(2030))

    //4) Вывести заданную дату в формате "01 Aug 2021,   23:39", в котором дата локализована для вывода в США (US).
    println(getFormattedDateTime(LocalDateTime.now()))
}
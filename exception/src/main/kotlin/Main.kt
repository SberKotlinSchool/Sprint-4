
fun main() {
    val saveClient = ClientService().saveClient(
        Client(
            firstName = "Имя",
            lastName = "Фамилия",
            phone = "81234567890",
            email = "email@email.ru",
            snils = "11223344595",
            version = 0
        )
    )

    val saveClient2 = ClientService().saveClient(
        Client(
            firstName = "иРмяпппппппппgппппппппппп",
            lastName = "",
            phone = "1123456789067a",
            email = "emAail@emaAilru",
            snils = "11223344596",
            version = 1
        )
    )

    val saveClient3 = ClientService().saveClient(
        Client(
            firstName = "",
            lastName = "Rffdghgваав",
            phone = "11234",
            email = "emAailemaAilruывваываывпвапвапвапвапваппвапвапвапвапвапвапвапвп4324234",
            snils = "011223344595",
            version = 2
        )
    )
}
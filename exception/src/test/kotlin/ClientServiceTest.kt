import org.junit.jupiter.api.*

class ClientServiceTest {
    private val clientService = ClientService()
    private val comparator = { er1: ErrorCode, er2: ErrorCode -> er1.code - er2.code }
    private val client: Client =
        Client("Анна", "Александренко", phone = "81234567891", "ivanova@ma.ru", "71033475050", version = 1)

    @TestFactory
    fun invalidPhoneNumber() = listOf(
        client.copy(phone = " ") to arrayOf(ErrorCode.PHONE_IS_NULL),
        client.copy(phone = null) to arrayOf(ErrorCode.PHONE_IS_NULL),
        client.copy(phone = "123")
                to arrayOf(
            ErrorCode.INVALID_PHONE_SIZE,
            ErrorCode.INVALID_FIRST_CHARACTER_OF_PHONE
        ),
        client.copy(phone = "123hgd")
                to arrayOf(
            ErrorCode.INVALID_PHONE_SIZE,
            ErrorCode.INVALID_FIRST_CHARACTER_OF_PHONE,
            ErrorCode.INVALID_CHARACTER
        ),
        client.copy(phone = "82345678932165") to arrayOf(ErrorCode.INVALID_PHONE_SIZE)
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("for '${input.phone}' expected ${expected.size} errors") {
            val exception = assertThrows<ValidationException> {
                clientService.saveClient(input)
            }
            Assertions.assertArrayEquals(
                expected.sortedArrayWith(comparator),
                exception.errorCode.sortedArrayWith(comparator)
            )
        }
    }

    @TestFactory
    fun invalidFirstName() = listOf(
        client.copy(firstName = null) to arrayOf(ErrorCode.NAME_IS_NULL),
        client.copy(firstName = "Anna") to arrayOf(ErrorCode.INVALID_CHARACTER_IN_FIRST_NAME),
        client.copy(firstName = "Анна Софья Генриетта")
                to arrayOf(
            ErrorCode.INVALID_CHARACTER_IN_FIRST_NAME,
            ErrorCode.INVALID_FIRST_NAME_SIZE
        )
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("for '${input.firstName}' expected ${expected.size} errors") {
            val exception = assertThrows<ValidationException> {
                clientService.saveClient(input)
            }
            Assertions.assertArrayEquals(
                expected.sortedArrayWith(comparator),
                exception.errorCode.sortedArrayWith(comparator)
            )
        }
    }

    @TestFactory
    fun invalidLastName() = listOf(
        client.copy(lastName = null) to arrayOf(ErrorCode.LAST_NAME_IS_NULL),
        client.copy(lastName = "Ivanova") to arrayOf(ErrorCode.INVALID_CHARACTER_IN_LAST_NAME),
        client.copy(lastName = "Александренко-Ковалевская")
                to arrayOf(
            ErrorCode.INVALID_CHARACTER_IN_LAST_NAME,
            ErrorCode.INVALID_LAST_NAME_SIZE
        )
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("for '${input.lastName}' expected ${expected.size} errors") {
            val exception = assertThrows<ValidationException> {
                clientService.saveClient(input)
            }
            Assertions.assertArrayEquals(
                expected.sortedArrayWith(comparator),
                exception.errorCode.sortedArrayWith(comparator)
            )
        }
    }

    @TestFactory
    fun invalidEmail() = listOf(
        client.copy(email = null) to arrayOf(ErrorCode.EMAIL_IS_NULL),
        client.copy(email = "иванова@mail.ru") to arrayOf(ErrorCode.INVALID_CHARACTER_IN_EMAIL),
        client.copy(email = "i12312312312312312312312312312123123123@mail.ru") to arrayOf(ErrorCode.INVALID_EMAIL_SIZE),
        client.copy(email = "ivanovaui.ru.") to arrayOf(ErrorCode.INVALID_EMAIL_FORMAT)
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("for '${input.email}' expected ${expected.size} errors") {
            val exception = assertThrows<ValidationException> {
                clientService.saveClient(input)
            }
            Assertions.assertArrayEquals(
                expected.sortedArrayWith(comparator),
                exception.errorCode.sortedArrayWith(comparator)
            )
        }
    }

    @TestFactory
    fun invalidSNILS() = listOf(
        client.copy(snils = null) to arrayOf(ErrorCode.SNILS_IS_NULL),
        client.copy(snils = "12354") to arrayOf(ErrorCode.INVALID_SNILS_SIZE),
        client.copy(snils = "123gh678945") to arrayOf(ErrorCode.INVALID_CHARACTER_IN_SNILS, ErrorCode.INVALID_SNILS),
        client.copy(snils = "12345678912") to arrayOf(ErrorCode.INVALID_SNILS)
    ).map { (input, expected) ->
        DynamicTest.dynamicTest("for '${input.snils}' expected ${expected.size} errors") {
            val exception = assertThrows<ValidationException> {
                clientService.saveClient(input)
            }
            Assertions.assertArrayEquals(
                expected.sortedArrayWith(comparator),
                exception.errorCode.sortedArrayWith(comparator)
            )
        }
    }

    @Test
    fun correctTest() {
        assertDoesNotThrow { clientService.saveClient(client) }
    }
}
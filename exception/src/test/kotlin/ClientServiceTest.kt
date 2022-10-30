import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(exception.errorCode[0], ErrorCode.INVALID_CHARACTER)
    }

    @ParameterizedTest
    @MethodSource("getClientsWithInvalidPhone")
    fun `fail save client - with invalid phone`(client: Client, expectedErrors: Array<ErrorCode>) {
        val actual = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertContentEquals(expectedErrors, actual.errorCode)
    }

    @ParameterizedTest
    @MethodSource("getClientsWithInvalidFirstName")
    fun `fail save client - with invalid first name`(client: Client, expectedErrors: Array<ErrorCode>) {
        val actual = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertContentEquals(expectedErrors, actual.errorCode)
    }

    @ParameterizedTest
    @MethodSource("getClientsWithInvalidLastName")
    fun `fail save client - with invalid last name`(client: Client, expectedErrors: Array<ErrorCode>) {
        val actual = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertContentEquals(expectedErrors, actual.errorCode)
    }

    @ParameterizedTest
    @MethodSource("getClientsWithInvalidEmail")
    fun `fail save client - with invalid email`(client: Client, expectedErrors: Array<ErrorCode>) {
        val actual = assertFailsWith<ValidationException> {
            clientService.saveClient(client)
        }

        assertContentEquals(expectedErrors, actual.errorCode)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

    companion object {
        private val gson = Gson()

        @JvmStatic
        fun getClientsWithInvalidPhone(): List<Arguments> =
            getClientsFromJson("/fail/user_with_bad_phone.json")

        @JvmStatic
        fun getClientsWithInvalidFirstName(): List<Arguments> =
            getClientsFromJson("/fail/user_with_bad_firstname.json")

        @JvmStatic
        fun getClientsWithInvalidLastName(): List<Arguments> =
            getClientsFromJson("/fail/user_with_bad_lastname.json")

        @JvmStatic
        fun getClientsWithInvalidEmail(): List<Arguments> =
            getClientsFromJson("/fail/user_with_bad_email.json")

        private fun getClientsFromJson(fileName: String): List<Arguments> =
            ClientServiceTest::class.java.getResource(fileName)
                .takeIf { it != null }
                ?.let {
                    val targetClassToken = object : TypeToken<Array<Map<String, Any>>>() {}.type
                    gson.fromJson(it.readText(), targetClassToken) as Array<Map<String, Any>>
                }
                ?.map {
                    val client = fromMapToObject<Client>(it["client"])
                    val errors = fromMapToObject<Array<ErrorCode>>(it["errors"])
                    Arguments.of(client, errors)
                }
                ?.toList()
                ?: throw Exception("Что-то пошло не так))")

        private inline fun <reified T> fromMapToObject(client: Any?): T {
            val clientJson = gson.toJson(client)
            return gson.fromJson(clientJson, T::class.java)
        }
    }
}

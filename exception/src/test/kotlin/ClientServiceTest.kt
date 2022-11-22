import ErrorCode.*
import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContentEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

internal class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(INVALID_CHARACTER, INVALID_LENGTH), exception.propDescMap["phone"])
    }

    @Test
    fun `fail save client - firstName validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_firstname.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(NULL_STRING), exception.propDescMap["firstName"])
    }

    @Test
    fun `fail save client - lastName validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_lastname.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(INVALID_LENGTH), exception.propDescMap["lastName"])
    }

    @Test
    fun `fail save client - email validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(DOMAIN_NOT_FOUND), exception.propDescMap["email"])
    }


    @Test
    fun `fail save client - snils validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertContentEquals(listOf(INVALID_CONTROL_NUM), exception.propDescMap["snils"])
    }


    @Test
    fun `fail save client - validation errors`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val exception = assertFailsWith<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertAll(
            { assertContentEquals(listOf(NULL_STRING), exception.propDescMap["firstName"]) },
            { assertContentEquals(listOf(INVALID_LENGTH), exception.propDescMap["lastName"]) },
            { assertContentEquals(listOf(INVALID_CHARACTER, INVALID_LENGTH), exception.propDescMap["phone"]) },
            { assertContentEquals(listOf(INVALID_CONTROL_NUM), exception.propDescMap["snils"]) },
            { assertContentEquals(listOf(DOMAIN_NOT_FOUND), exception.propDescMap["email"]) }
        )
    }




    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
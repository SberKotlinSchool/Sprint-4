import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - first name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_fn.json")
        assertThrows<InvalidFirstName> {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - last name validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_ln.json")
        assertThrows<InvalidLastName> {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - phone validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        assertThrows<InvalidPhoneNumber> {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - e-mail validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_email.json")
        assertThrows<InvalidEmail> {
            clientService.saveClient(client)
        }
    }

    @Test
    fun `fail save client - insurance validation error`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        assertThrows<InvalidInsuranceNumber> {
            clientService.saveClient(client)
        }
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
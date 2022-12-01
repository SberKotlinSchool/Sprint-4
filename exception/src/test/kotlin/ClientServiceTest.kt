import com.google.gson.Gson
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
        assertEquals(2, result.version)
    }

    @Test
    fun `exception in snils null pointer`() {
        val client = getClientFromJson("/fail/user_with_snils_null_pointer.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(30, exception.errorCode.get(0).code)
    }

    @Test
    fun `exception in snils checksum`() {
        val client = getClientFromJson("/fail/user_with_snils_bad_checksum.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }
        assertEquals(31, exception.errorCode.get(0).code)
    }

    @Test
    fun `exception in snils numbers of digits`() {
        val client = getClientFromJson("/fail/user_with_snils_bad_numbers_of_digits.json")
        val exception = assertThrows<ValidationException>("Ожидаемая ошибка") {
            clientService.saveClient(client)
        }

        assertEquals(31, exception.errorCode.get(0).code)
    }

    @Test
    fun `exception in phone bad first number`() {
        val client = getClientFromJson("/fail/user_with_phone_bad_format_first_number.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(11, exception.errorCode.get(0).code)
    }

    @Test
    fun `exception in phone bad numbers of digits`() {
        val client = getClientFromJson("/fail/user_with_phone_bad_format_numbers_of_digits.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(11, exception.errorCode.get(0).code)
    }

    @Test
    fun `exception in phone null pointer`() {
        val client = getClientFromJson("/fail/user_with_phone_bad_format_null_pointer.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(10, exception.errorCode.get(0).code)
    }

    @Test
    fun `exception in email null pointer`() {
        val client = getClientFromJson("/fail/user_with_email_null_pointer.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(20, exception.errorCode.get(0).code)
        assertEquals(1, exception.errorCode.size)
    }

    @Test
    fun `exception in email bad format`() {
        val client = getClientFromJson("/fail/user_with_email_bad_format.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(21, exception.errorCode.get(0).code)
        assertEquals(1, exception.errorCode.size)
    }

    @Test
    fun `exception in firstname and lastname bad format`() {
        val client = getClientFromJson("/fail/user_with_name_bad_format.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(41, exception.errorCode.get(0).code)
        assertEquals(41, exception.errorCode.get(1).code)
        assertEquals(2, exception.errorCode.size)
    }

    @Test
    fun `exception in firstname null pointer`() {
        val client = getClientFromJson("/fail/user_with_firstname_null_pointer.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(40, exception.errorCode.get(0).code)
        assertEquals(1, exception.errorCode.size)
    }

    @Test
    fun `exception in lastname null pointer`() {
        val client = getClientFromJson("/fail/user_with_lastname_null_pointer.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(40, exception.errorCode.get(0).code)
        assertEquals(1, exception.errorCode.size)
    }

    @Test
    fun `exception all null pointer`() {
        val client = getClientFromJson("/fail/user_data_all_null.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(5, exception.errorCode.size)
        assertEquals(0, exception.errorCode.get(0).code % 10)
        assertEquals(0, exception.errorCode.get(1).code % 10)
        assertEquals(0, exception.errorCode.get(2).code % 10)
        assertEquals(0, exception.errorCode.get(3).code % 10)
        assertEquals(0, exception.errorCode.get(4).code % 10)
    }

    @Test
    fun `exception all bad format`() {
        val client = getClientFromJson("/fail/user_data_all_badformat.json")

        val exception = assertThrows<ValidationException> { clientService.saveClient(client) }
        assertEquals(5, exception.errorCode.size)
        assertEquals(1, exception.errorCode.get(0).code % 10)
        assertEquals(1, exception.errorCode.get(1).code % 10)
        assertEquals(1, exception.errorCode.get(2).code % 10)
        assertEquals(1, exception.errorCode.get(3).code % 10)
        assertEquals(1, exception.errorCode.get(4).code % 10)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
import com.google.gson.Gson
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class ClientServiceTest {

    private val gson = Gson()
    private val clientService = ClientService()

    @Test
    fun `success save client correctly`() {
        val client = getClientFromJson("/success/user.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `success save client correctly - 2`() {
        val client = getClientFromJson("/success/user2.json")
        val result = clientService.saveClient(client)
        assertNotNull(result)
    }

    @Test
    fun `fail save client - validation error - phone`() {
        val client = getClientFromJson("/fail/user_with_bad_phone.json")
        val expected = setOf(ErrorCode.INVALID_PHONE)

        val exception = assertFailsWith<ValidationException> { clientService.saveClient(client) }

        assertEquals(exception.errorCode.toSet(), expected)
    }

    @Test
    fun `fail save client - validation error - snils`() {
        val client = getClientFromJson("/fail/user_with_bad_snils.json")
        val expected = setOf(ErrorCode.INVALID_SNILS)

        val exception = assertFailsWith<ValidationException> { clientService.saveClient(client) }

        assertEquals(exception.errorCode.toSet(), expected)
    }

    @Test
    fun `fail save client - validation errors - phone,email,lastname`() {
        val client = getClientFromJson("/fail/user_data_corrupted.json")
        val expected = setOf(
            ErrorCode.INVALID_PHONE,
            ErrorCode.INVALID_EMAIL,
            ErrorCode.INVALID_LAST_NAME,
        )

        val exception = assertFailsWith<ValidationException> { clientService.saveClient(client) }

        assertEquals(exception.errorCode.toSet(), expected)
    }

    @Test
    fun `fail save client - validation errors - snils,email`() {
        val client = getClientFromJson("/fail/user_snils_email_corrupted.json")
        val expected = setOf(
            ErrorCode.INVALID_SNILS,
            ErrorCode.INVALID_EMAIL,
        )

        val exception = assertFailsWith<ValidationException> { clientService.saveClient(client) }

        assertEquals(exception.errorCode.toSet(), expected)
    }

    @Test
    fun `fail save client - validation errors - all wrong`() {
        val client = getClientFromJson("/fail/user_all_wrong.json")
        val expected = setOf(
            ErrorCode.INVALID_FIRST_NAME,
            ErrorCode.INVALID_LAST_NAME,
            ErrorCode.INVALID_PHONE,
            ErrorCode.INVALID_SNILS,
            ErrorCode.INVALID_EMAIL,
        )


        val exception = assertFailsWith<ValidationException> { clientService.saveClient(client) }

         assertEquals(exception.errorCode.toSet(), expected)
    }

    private fun getClientFromJson(fileName: String): Client = this::class.java.getResource(fileName)
        .takeIf { it != null }
        ?.let { gson.fromJson(it.readText(), Client::class.java) }
        ?: throw Exception("Что-то пошло не так))")

}
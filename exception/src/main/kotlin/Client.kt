data class Client(
    val firstName: String?,
    val lastName: String?,
    val phone: String?,
    val email: String?,
    val snils: String?,
    var version: Int
) {
    fun incrementVersion() = version++
}

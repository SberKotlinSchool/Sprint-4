data class Client(
    var firstName: String?,
    var lastName: String?,
    var phone: String?,
    var email: String?,
    var snils: String?,
    var version: Int
) {
    fun incrementVersion() = version++
}
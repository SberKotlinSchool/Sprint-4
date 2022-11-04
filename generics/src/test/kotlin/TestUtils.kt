
fun <R> getField(instance: Any, propertyName: String): R {
    val field = instance.javaClass.getDeclaredField(propertyName)
    field.isAccessible = true
    @Suppress("UNCHECKED_CAST")
    return field.get(instance) as R
}

fun <R> setField(instance: Any, propertyName: String, value: R) {
    val field = instance.javaClass.getDeclaredField(propertyName)
    field.isAccessible = true
    field.set(instance, value)
}
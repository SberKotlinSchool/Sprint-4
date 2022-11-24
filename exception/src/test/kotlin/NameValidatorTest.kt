import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class NameValidatorTest {
    val nameValidator = NameValidator()

    private companion object {
        @JvmStatic
        fun nameArguments() = Stream.of(
            Arguments.of( emptyList<ErrorCode>(), "Вася")
            , Arguments.of(emptyList<ErrorCode>(), "ВВВВВВВВВВВВВВВВ")
            , Arguments.of(listOf(ErrorCode.INVALID_CYRILLIC), "Vasya")
            , Arguments.of(listOf(ErrorCode.INVALID_COUNT), "ВВВВВВВВВВВВВВВВВ")
            //, Arguments.of(listOf(ErrorCode.INVALID_CYRILLIC, ErrorCode.INVALID_COUNT), "ВВВВ1ВВВВВВВВВВВВ")
        )
    }

    @MethodSource("nameArguments")
    @ParameterizedTest
    fun validateName(expected: List<ErrorCode>, name: String) {
        assertEquals(expected, nameValidator.validate(name))
    }



}
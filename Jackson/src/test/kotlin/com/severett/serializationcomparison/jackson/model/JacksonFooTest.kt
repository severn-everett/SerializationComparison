package com.severett.serializationcomparison.jackson.model

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JacksonFooTest {
    private val objectMapper = ObjectMapper()

    @ParameterizedTest
    @MethodSource
    fun serialize(outputCallback: () -> String) {
        val givenOutput = outputCallback.invoke()
        Assertions.assertEquals("""{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}""", givenOutput)
    }

    @ParameterizedTest
    @MethodSource
    fun deserialize(inputStr: String, expectedObj: Any) {
        val givenObject = objectMapper.readValue(inputStr, expectedObj::class.java)
        Assertions.assertEquals(expectedObj, givenObject)
    }

    private fun serialize() = Stream.of(
        { objectMapper.writeValueAsString(pojoFoo) },
        { objectMapper.writeValueAsString(immutableFoo) },
        { objectMapper.writeValueAsString(defaultValueFoo) },
        { objectMapper.writeValueAsString(valueClassFoo) },
    )

    private fun deserialize() = Stream.of(
        Arguments.of(pojoFooStr, pojoFoo),
        Arguments.of(immutableFooStr, immutableFoo),
        Arguments.of(defaultValueFooStr, defaultValueFoo),
        Arguments.of(valueClassFooStr, valueClassFoo),
    )
}

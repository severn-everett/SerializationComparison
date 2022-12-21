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
    fun serialize(outputCallback: () -> String, expectedOutput: String) {
        val givenOutput = outputCallback.invoke()
        Assertions.assertEquals(expectedOutput, givenOutput)
    }

    @ParameterizedTest
    @MethodSource
    fun deserialize(inputStr: String, expectedObj: Any) {
        val givenObject = objectMapper.readValue(inputStr, expectedObj::class.java)
        Assertions.assertEquals(expectedObj, givenObject)
    }

    private fun serialize() = Stream.of(
        Arguments.of({ objectMapper.writeValueAsString(pojoFoo) }, """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""),
        Arguments.of({ objectMapper.writeValueAsString(immutableFoo) }, """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""),
        Arguments.of({ objectMapper.writeValueAsString(defaultValueFoo) }, """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""),
        Arguments.of({ objectMapper.writeValueAsString(valueClassFoo) }, """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""),
    )

    private fun deserialize() = Stream.of(
        Arguments.of(pojoFooStr, pojoFoo),
        Arguments.of(immutableFooStr, immutableFoo),
        Arguments.of(defaultValueFooStr, defaultValueFoo),
        Arguments.of(valueClassFooStr, valueClassFoo),
    )
}

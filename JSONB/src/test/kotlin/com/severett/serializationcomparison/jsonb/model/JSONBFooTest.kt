package com.severett.serializationcomparison.jsonb.model

import jakarta.json.bind.JsonbBuilder
import jakarta.json.bind.JsonbConfig
import jakarta.json.bind.config.PropertyOrderStrategy
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JSONBFooTest {
    private val jsonb = JsonbBuilder.create(
        JsonbConfig().withPropertyOrderStrategy(PropertyOrderStrategy.ANY)
    )

    @ParameterizedTest
    @MethodSource
    fun serialize(outputCallback: () -> String, expectedOutput: String) {
        val givenOutput = outputCallback.invoke()
        Assertions.assertEquals(expectedOutput, givenOutput)
    }

    @ParameterizedTest
    @MethodSource
    fun deserialize(inputStr: String, expectedObj: Any) {
        val givenObject = jsonb.fromJson(inputStr, expectedObj::class.java)
        Assertions.assertEquals(expectedObj, givenObject)
    }

    private fun serialize() = Stream.of(
        Arguments.of({ jsonb.toJson(pojoFoo) }, """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""),
        Arguments.of({ jsonb.toJson(immutableFoo) }, """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""),
        Arguments.of({ jsonb.toJson(defaultValueFoo) }, """{"fizz":"FUZZ"}"""),
        Arguments.of({ jsonb.toJson(valueClassFoo) }, """{"bizz":5}"""),
    )

    private fun deserialize() = Stream.of(
        Arguments.of(pojoFooStr, pojoFoo),
        Arguments.of(immutableFooStr, immutableFoo),
        Arguments.of(defaultValueFooStr, defaultValueFoo),
        Arguments.of(valueClassFooStr, valueClassFoo),
    )
}

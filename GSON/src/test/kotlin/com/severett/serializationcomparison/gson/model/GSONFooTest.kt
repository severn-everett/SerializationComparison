package com.severett.serializationcomparison.gson.model

import com.google.gson.Gson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GSONFooTest {
    private val gson = Gson()

    @ParameterizedTest
    @MethodSource
    fun serialize(outputCallback: () -> String, expectedOutput: String) {
        val givenOutput = outputCallback.invoke()
        assertEquals(expectedOutput, givenOutput)
    }

    @ParameterizedTest
    @MethodSource
    fun deserialize(inputStr: String, expectedObj: Any) {
        val givenObject = gson.fromJson(inputStr, expectedObj::class.java)
        assertEquals(expectedObj, givenObject)
    }

    private fun serialize() = Stream.of(
        Arguments.of({ gson.toJson(pojoFoo) }, """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""),
        Arguments.of({ gson.toJson(immutableFoo) }, """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""),
        Arguments.of({ gson.toJson(defaultValueFoo) }, """{"fizz":"FUZZ"}"""),
        Arguments.of({ gson.toJson(valueClassFoo) }, """{"bizz":5}"""),
    )

    private fun deserialize() = Stream.of(
        Arguments.of(pojoFooStr, pojoFoo),
        Arguments.of(immutableFooStr, immutableFoo),
        Arguments.of(defaultValueFooStr, defaultValueFoo),
        Arguments.of(valueClassFooStr, valueClassFoo),
    )
}
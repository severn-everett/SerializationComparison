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
    fun serialize(outputCallback: () -> String) {
        val givenOutput = outputCallback.invoke()
        assertEquals("""{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}""", givenOutput)
    }

    @ParameterizedTest
    @MethodSource
    fun deserialize(inputStr: String, expectedObj: Any) {
        val givenObject = gson.fromJson(inputStr, expectedObj::class.java)
        assertEquals(expectedObj, givenObject)
    }

    private fun serialize() = Stream.of(
        { gson.toJson(pojoFoo) },
        { gson.toJson(immutableFoo) },
        { gson.toJson(defaultValueFoo) },
        { gson.toJson(valueClassFoo) },
    )

    private fun deserialize() = Stream.of(
        Arguments.of(pojoFooStr, pojoFoo),
        Arguments.of(immutableFooStr, immutableFoo),
        Arguments.of(defaultValueFooStr, defaultValueFoo),
        Arguments.of(valueClassFooStr, valueClassFoo),
    )
}
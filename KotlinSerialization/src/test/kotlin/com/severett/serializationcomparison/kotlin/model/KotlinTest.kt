package com.severett.serializationcomparison.kotlin.model

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KotlinTest {
    @ParameterizedTest
    @MethodSource
    fun serialize(outputCallback: () -> String) {
        val givenOutput = outputCallback.invoke()
        Assertions.assertEquals("""{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}""", givenOutput)
    }

    @ParameterizedTest
    @MethodSource
    fun deserialize(inputCallback: () -> Any, expectedObj: Any) {
        val givenObject = inputCallback.invoke()
        Assertions.assertEquals(expectedObj, givenObject)
    }

    private fun serialize() = Stream.of(
        { Json.encodeToString(pojoFoo) },
        { Json.encodeToString(immutableFoo) },
        { Json.encodeToString(defaultValueFoo) },
        { Json.encodeToString(valueClassFoo) },
    )

    private fun deserialize() = Stream.of(
        Arguments.of({ Json.decodeFromString<PojoFoo>(pojoFooStr) }, pojoFoo),
        Arguments.of({ Json.decodeFromString<ImmutableFoo>(immutableFooStr) }, immutableFoo),
        Arguments.of({ Json.decodeFromString<DefaultValueFoo>(defaultValueFooStr) }, defaultValueFoo),
        Arguments.of({ Json.decodeFromString<ValueClassFoo>(valueClassFooStr) }, valueClassFoo),
    )
}

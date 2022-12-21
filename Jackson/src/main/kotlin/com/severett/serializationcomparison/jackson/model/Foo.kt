package com.severett.serializationcomparison.jackson.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.severett.serializationcomparison.jackson.serde.ValueClassFooDeserializer
import com.severett.serializationcomparison.jackson.serde.ValueClassFooSerializer

data class PojoFoo(var fizz: String, var bizz: Int, var bazz: List<String>) {
    constructor() : this("", 0, emptyList())
}

data class ImmutableFoo(
    @param:JsonProperty("fizz") val fizz: String,
    @param:JsonProperty("bizz") val bizz: Int,
    @param:JsonProperty("bazz") val bazz: List<String>
)

data class DefaultValueFoo(val fizz: String = "FUZZ")

@JsonSerialize(using = ValueClassFooSerializer::class)
@JsonDeserialize(using = ValueClassFooDeserializer::class)
data class ValueClassFoo(
    var bizz: UInt
) {
    constructor() : this(0u)

    companion object {
        const val BIZZ_FIELD = "bizz"
    }
}

val pojoFoo = PojoFoo(fizz = "FUZZ", bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val immutableFoo = ImmutableFoo(fizz = "FUZZ", bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val defaultValueFoo = DefaultValueFoo()
val valueClassFoo = ValueClassFoo(bizz = 5u)

const val pojoFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val immutableFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val defaultValueFooStr = """{}"""
const val valueClassFooStr = """{"bizz":5}"""

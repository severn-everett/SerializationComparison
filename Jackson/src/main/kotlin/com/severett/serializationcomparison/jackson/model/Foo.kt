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

data class DefaultValueFoo(var fizz: String = "FUZZ", var bizz: Int, var bazz: List<String>) {
    constructor() : this(bizz = 0, bazz = emptyList())
}

@JsonSerialize(using = ValueClassFooSerializer::class)
@JsonDeserialize(using = ValueClassFooDeserializer::class)
data class ValueClassFoo(var fizz: String, var bizz: UInt, var bazz: List<String>) {
    constructor() : this("", 0u, emptyList())

    companion object {
        const val FIZZ_FIELD = "fizz"
        const val BIZZ_FIELD = "bizz"
        const val BAZZ_FIELD = "bazz"
    }
}

val pojoFoo = PojoFoo(fizz = "FUZZ", bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val immutableFoo = ImmutableFoo(fizz = "FUZZ", bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val defaultValueFoo = DefaultValueFoo(bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val valueClassFoo = ValueClassFoo(fizz = "FUZZ", bizz = 5u, bazz = listOf("BUZZ", "BOZZ"))

const val pojoFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val immutableFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val defaultValueFooStr = """{"bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val valueClassFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""

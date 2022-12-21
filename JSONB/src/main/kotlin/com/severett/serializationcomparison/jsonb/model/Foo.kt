package com.severett.serializationcomparison.jsonb.model

import com.severett.serializationcomparison.jsonb.serde.ValueClassFooDeserializer
import com.severett.serializationcomparison.jsonb.serde.ValueClassFooSerializer
import jakarta.json.bind.annotation.JsonbCreator
import jakarta.json.bind.annotation.JsonbProperty
import jakarta.json.bind.annotation.JsonbPropertyOrder
import jakarta.json.bind.annotation.JsonbTypeDeserializer
import jakarta.json.bind.annotation.JsonbTypeSerializer

@JsonbPropertyOrder("fizz", "bizz", "bazz")
data class PojoFoo(var fizz: String, var bizz: Int, var bazz: List<String>) {
    constructor() : this("", 0, emptyList())
}

@JsonbPropertyOrder("fizz", "bizz", "bazz")
data class ImmutableFoo @JsonbCreator constructor(
    @JsonbProperty("fizz") val fizz: String,
    @JsonbProperty("bizz") val bizz: Int,
    @JsonbProperty("bazz") val bazz: List<String>
)

@JsonbPropertyOrder("fizz", "bizz", "bazz")
data class DefaultValueFoo(var fizz: String = "FUZZ", var bizz: Int, var bazz: List<String>) {
    constructor() : this(bizz = 0, bazz = emptyList())
}

@JsonbTypeDeserializer(ValueClassFooDeserializer::class)
@JsonbTypeSerializer(ValueClassFooSerializer::class)
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

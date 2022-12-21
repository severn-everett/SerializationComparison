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

data class DefaultValueFoo(val fizz: String = "FUZZ")

@JsonbTypeDeserializer(ValueClassFooDeserializer::class)
@JsonbTypeSerializer(ValueClassFooSerializer::class)
data class ValueClassFoo(var bizz: UInt) {
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

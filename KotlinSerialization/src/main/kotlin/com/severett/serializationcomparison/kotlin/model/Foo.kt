package com.severett.serializationcomparison.kotlin.model

import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data class PojoFoo(var fizz: String, var bizz: Int, var bazz: List<String>) {
    constructor() : this("", 0, emptyList())
}

@Serializable
data class ImmutableFoo(val fizz: String, val bizz: Int, val bazz: List<String>)

@Serializable
@OptIn(ExperimentalSerializationApi::class)
data class DefaultValueFoo(@EncodeDefault val fizz: String = "FUZZ", var bizz: Int, var bazz: List<String>) {
    constructor() : this(bizz = 0, bazz = emptyList())
}

@Serializable
data class ValueClassFoo(var fizz: String, var bizz: UInt, var bazz: List<String>) {
    constructor() : this("", 0u, emptyList())
}

val pojoFoo = PojoFoo(fizz = "FUZZ", bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val immutableFoo = ImmutableFoo(fizz = "FUZZ", bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val defaultValueFoo = DefaultValueFoo(bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val valueClassFoo = ValueClassFoo(fizz = "FUZZ", bizz = 5u, bazz = listOf("BUZZ", "BOZZ"))

const val pojoFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val immutableFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val defaultValueFooStr = """{"bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val valueClassFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""

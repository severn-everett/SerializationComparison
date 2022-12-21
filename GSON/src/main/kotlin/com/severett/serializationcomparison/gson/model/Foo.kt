package com.severett.serializationcomparison.gson.model

data class PojoFoo(var fizz: String, var bizz: Int, var bazz: List<String>) {
    constructor() : this("", 0, emptyList())
}

data class ImmutableFoo(val fizz: String, val bizz: Int, val bazz: List<String>)

data class DefaultValueFoo(val fizz: String = "FUZZ")

data class ValueClassFoo(var bizz: UInt) {
    constructor() : this(0u)
}

val pojoFoo = PojoFoo(fizz = "FUZZ", bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val immutableFoo = ImmutableFoo(fizz = "FUZZ", bizz = 5, bazz = listOf("BUZZ", "BOZZ"))
val defaultValueFoo = DefaultValueFoo()
val valueClassFoo = ValueClassFoo(bizz = 5u)

const val pojoFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val immutableFooStr = """{"fizz":"FUZZ","bizz":5,"bazz":["BUZZ","BOZZ"]}"""
const val defaultValueFooStr = """{}"""
const val valueClassFooStr = """{"bizz":5}"""

package com.severett.serializationcomparison.kotlin

import com.severett.serializationcomparison.kotlin.model.DefaultValueFoo
import com.severett.serializationcomparison.kotlin.model.ImmutableFoo
import com.severett.serializationcomparison.kotlin.model.PojoFoo
import com.severett.serializationcomparison.kotlin.model.ValueClassFoo
import com.severett.serializationcomparison.kotlin.model.defaultValueFoo
import com.severett.serializationcomparison.kotlin.model.defaultValueFooStr
import com.severett.serializationcomparison.kotlin.model.immutableFoo
import com.severett.serializationcomparison.kotlin.model.immutableFooStr
import com.severett.serializationcomparison.kotlin.model.pojoFoo
import com.severett.serializationcomparison.kotlin.model.pojoFooStr
import com.severett.serializationcomparison.kotlin.model.valueClassFoo
import com.severett.serializationcomparison.kotlin.model.valueClassFooStr
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
class SerializationComparison {
    @Benchmark
    fun serializePojoFoo(): String = Json.encodeToString(pojoFoo)

    @Benchmark
    fun serializeImmutableFoo(): String = Json.encodeToString(immutableFoo)

    @Benchmark
    fun serializeDefaultValueFoo(): String = Json.encodeToString(defaultValueFoo)

    @Benchmark
    fun serializeValueClassFoo(): String = Json.encodeToString(valueClassFoo)

    @Benchmark
    fun deserializePojoFoo(): PojoFoo = Json.decodeFromString(pojoFooStr)

    @Benchmark
    fun deserializeImmutableFoo(): ImmutableFoo = Json.decodeFromString(immutableFooStr)

    @Benchmark
    fun deserializeDefaultValueFoo(): DefaultValueFoo = Json.decodeFromString(defaultValueFooStr)

    @Benchmark
    fun deserializeValueClassFoo(): ValueClassFoo = Json.decodeFromString(valueClassFooStr)
}

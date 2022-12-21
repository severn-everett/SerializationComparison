package com.severett.serializationcomparison.jackson

import com.fasterxml.jackson.databind.ObjectMapper
import com.severett.serializationcomparison.jackson.model.DefaultValueFoo
import com.severett.serializationcomparison.jackson.model.ImmutableFoo
import com.severett.serializationcomparison.jackson.model.PojoFoo
import com.severett.serializationcomparison.jackson.model.ValueClassFoo
import com.severett.serializationcomparison.jackson.model.defaultValueFoo
import com.severett.serializationcomparison.jackson.model.defaultValueFooStr
import com.severett.serializationcomparison.jackson.model.immutableFoo
import com.severett.serializationcomparison.jackson.model.immutableFooStr
import com.severett.serializationcomparison.jackson.model.pojoFoo
import com.severett.serializationcomparison.jackson.model.pojoFooStr
import com.severett.serializationcomparison.jackson.model.valueClassFoo
import com.severett.serializationcomparison.jackson.model.valueClassFooStr
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
class SerializationComparison {
    private val objectMapper = ObjectMapper()

    @Benchmark
    fun serializePojoFoo(): String = objectMapper.writeValueAsString(pojoFoo)

    @Benchmark
    fun serializeImmutableFoo(): String = objectMapper.writeValueAsString(immutableFoo)

    @Benchmark
    fun serializeDefaultValueFoo(): String = objectMapper.writeValueAsString(defaultValueFoo)

    @Benchmark
    fun serializeValueClassFoo(): String = objectMapper.writeValueAsString(valueClassFoo)

    @Benchmark
    fun deserializePojoFoo(): PojoFoo = objectMapper.readValue(pojoFooStr, PojoFoo::class.java)

    @Benchmark
    fun deserializeImmutableFoo(): ImmutableFoo = objectMapper.readValue(immutableFooStr, ImmutableFoo::class.java)

    @Benchmark
    fun deserializeDefaultValueFoo(): DefaultValueFoo = objectMapper.readValue(defaultValueFooStr, DefaultValueFoo::class.java)

    @Benchmark
    fun deserializeValueClassFoo(): ValueClassFoo = objectMapper.readValue(valueClassFooStr, ValueClassFoo::class.java)
}

package com.severett.serializationcomparison.jsonb

import com.severett.serializationcomparison.jsonb.model.DefaultValueFoo
import com.severett.serializationcomparison.jsonb.model.ImmutableFoo
import com.severett.serializationcomparison.jsonb.model.PojoFoo
import com.severett.serializationcomparison.jsonb.model.ValueClassFoo
import com.severett.serializationcomparison.jsonb.model.defaultValueFoo
import com.severett.serializationcomparison.jsonb.model.defaultValueFooStr
import com.severett.serializationcomparison.jsonb.model.immutableFoo
import com.severett.serializationcomparison.jsonb.model.immutableFooStr
import com.severett.serializationcomparison.jsonb.model.pojoFoo
import com.severett.serializationcomparison.jsonb.model.pojoFooStr
import com.severett.serializationcomparison.jsonb.model.valueClassFoo
import com.severett.serializationcomparison.jsonb.model.valueClassFooStr
import jakarta.json.bind.JsonbBuilder
import jakarta.json.bind.JsonbConfig
import jakarta.json.bind.config.PropertyOrderStrategy
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
open class SerializationComparison {
    private val jsonb = JsonbBuilder.create(
        JsonbConfig().withPropertyOrderStrategy(PropertyOrderStrategy.ANY)
    )

    @Benchmark
    fun serializePojoFoo(): String = jsonb.toJson(pojoFoo)

    @Benchmark
    fun serializeImmutableFoo(): String = jsonb.toJson(immutableFoo)

    @Benchmark
    fun serializeDefaultValueFoo(): String = jsonb.toJson(defaultValueFoo)

    @Benchmark
    fun serializeValueClassFoo(): String = jsonb.toJson(valueClassFoo)

    @Benchmark
    fun deserializePojoFoo(): PojoFoo = jsonb.fromJson(pojoFooStr, PojoFoo::class.java)

    @Benchmark
    fun deserializeImmutableFoo(): ImmutableFoo = jsonb.fromJson(immutableFooStr, ImmutableFoo::class.java)

    @Benchmark
    fun deserializeDefaultValueFoo(): DefaultValueFoo = jsonb.fromJson(defaultValueFooStr, DefaultValueFoo::class.java)

    @Benchmark
    fun deserializeValueClassFoo(): ValueClassFoo = jsonb.fromJson(valueClassFooStr, ValueClassFoo::class.java)
}

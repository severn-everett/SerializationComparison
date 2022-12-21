package com.severett.serializationcomparison.gson

import com.google.gson.Gson
import com.severett.serializationcomparison.gson.model.DefaultValueFoo
import com.severett.serializationcomparison.gson.model.ImmutableFoo
import com.severett.serializationcomparison.gson.model.PojoFoo
import com.severett.serializationcomparison.gson.model.ValueClassFoo
import com.severett.serializationcomparison.gson.model.defaultValueFoo
import com.severett.serializationcomparison.gson.model.defaultValueFooStr
import com.severett.serializationcomparison.gson.model.immutableFoo
import com.severett.serializationcomparison.gson.model.immutableFooStr
import com.severett.serializationcomparison.gson.model.pojoFoo
import com.severett.serializationcomparison.gson.model.pojoFooStr
import com.severett.serializationcomparison.gson.model.valueClassFoo
import com.severett.serializationcomparison.gson.model.valueClassFooStr
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State

@State(Scope.Benchmark)
open class SerializationComparison {
    private val gson = Gson()

    @Benchmark
    fun serializePojoFoo(): String = gson.toJson(pojoFoo)

    @Benchmark
    fun serializeImmutableFoo(): String = gson.toJson(immutableFoo)

    @Benchmark
    fun serializeDefaultValueFoo(): String = gson.toJson(defaultValueFoo)

    @Benchmark
    fun serializeValueClassFoo(): String = gson.toJson(valueClassFoo)

    @Benchmark
    fun deserializePojoFoo(): PojoFoo = gson.fromJson(pojoFooStr, PojoFoo::class.java)

    @Benchmark
    fun deserializeImmutableFoo(): ImmutableFoo = gson.fromJson(immutableFooStr, ImmutableFoo::class.java)

    @Benchmark
    fun deserializeDefaultValueFoo(): DefaultValueFoo = gson.fromJson(defaultValueFooStr, DefaultValueFoo::class.java)

    @Benchmark
    fun deserializeValueClassFoo(): ValueClassFoo = gson.fromJson(valueClassFooStr, ValueClassFoo::class.java)
}

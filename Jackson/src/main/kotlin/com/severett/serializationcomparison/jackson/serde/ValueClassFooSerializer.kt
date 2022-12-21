package com.severett.serializationcomparison.jackson.serde

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.severett.serializationcomparison.jackson.model.ValueClassFoo

class ValueClassFooSerializer : JsonSerializer<ValueClassFoo>() {
    override fun serialize(value: ValueClassFoo, gen: JsonGenerator, serializers: SerializerProvider?) {
        gen.writeStartObject()
        gen.writeNumberField(ValueClassFoo.BIZZ_FIELD, value.bizz.toInt())
        gen.writeEndObject()
    }
}

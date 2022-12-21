package com.severett.serializationcomparison.jsonb.serde

import com.severett.serializationcomparison.jsonb.model.ValueClassFoo
import jakarta.json.bind.serializer.JsonbSerializer
import jakarta.json.bind.serializer.SerializationContext
import jakarta.json.stream.JsonGenerator

class ValueClassFooSerializer : JsonbSerializer<ValueClassFoo> {
    override fun serialize(valueClassFoo: ValueClassFoo, generator: JsonGenerator, ctx: SerializationContext?) {
        generator.writeStartObject()
        generator.write(ValueClassFoo.FIZZ_FIELD, valueClassFoo.fizz)
        generator.write(ValueClassFoo.BIZZ_FIELD, valueClassFoo.bizz.toInt())
        generator.writeStartArray(ValueClassFoo.BAZZ_FIELD)
        valueClassFoo.bazz.forEach(generator::write)
        generator.writeEnd()
        generator.writeEnd()
    }
}

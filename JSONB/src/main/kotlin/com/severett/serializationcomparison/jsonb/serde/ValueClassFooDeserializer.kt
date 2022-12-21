package com.severett.serializationcomparison.jsonb.serde

import com.severett.serializationcomparison.jsonb.model.ValueClassFoo
import jakarta.json.JsonString
import jakarta.json.bind.serializer.DeserializationContext
import jakarta.json.bind.serializer.JsonbDeserializer
import jakarta.json.stream.JsonParser
import java.lang.reflect.Type

class ValueClassFooDeserializer : JsonbDeserializer<ValueClassFoo> {
    override fun deserialize(jsonParser: JsonParser, ctx: DeserializationContext?, rtType: Type?): ValueClassFoo {
        var bizz: UInt? = null
        while (jsonParser.hasNext()) {
            val event = jsonParser.next()
            if (event != JsonParser.Event.KEY_NAME) continue
            when (jsonParser.string) {
                ValueClassFoo.BIZZ_FIELD -> {
                    jsonParser.next()
                    bizz = jsonParser.int.toUInt()
                }
            }
        }
        requireNotNull(bizz)
        return ValueClassFoo(bizz)
    }
}

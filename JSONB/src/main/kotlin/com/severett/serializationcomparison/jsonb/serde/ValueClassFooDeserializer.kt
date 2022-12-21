package com.severett.serializationcomparison.jsonb.serde

import com.severett.serializationcomparison.jsonb.model.ValueClassFoo
import jakarta.json.JsonString
import jakarta.json.bind.serializer.DeserializationContext
import jakarta.json.bind.serializer.JsonbDeserializer
import jakarta.json.stream.JsonParser
import java.lang.reflect.Type

class ValueClassFooDeserializer : JsonbDeserializer<ValueClassFoo> {
    override fun deserialize(jsonParser: JsonParser, ctx: DeserializationContext?, rtType: Type?): ValueClassFoo {
        var fizz: String? = null
        var bizz: UInt? = null
        var bazz: List<String>? = null
        while (jsonParser.hasNext()) {
            val event = jsonParser.next()
            if (event != JsonParser.Event.KEY_NAME) continue
            when (jsonParser.string) {
                ValueClassFoo.FIZZ_FIELD -> {
                    jsonParser.next()
                    fizz = jsonParser.string
                }
                ValueClassFoo.BIZZ_FIELD -> {
                    jsonParser.next()
                    bizz = jsonParser.int.toUInt()
                }
                ValueClassFoo.BAZZ_FIELD -> {
                    jsonParser.next()
                    bazz = jsonParser.array.getValuesAs(JsonString::class.java).map { it.string }
                }
            }
        }
        if (fizz != null && bizz != null && bazz != null) {
            return ValueClassFoo(fizz = fizz, bizz = bizz, bazz = bazz)
        } else {
            throw IllegalStateException("'fizz', 'bizz', and 'bazz' must be not null")
        }
    }
}

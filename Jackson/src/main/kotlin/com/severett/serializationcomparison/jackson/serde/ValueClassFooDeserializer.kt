package com.severett.serializationcomparison.jackson.serde

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.node.ArrayNode
import com.severett.serializationcomparison.jackson.model.ValueClassFoo

class ValueClassFooDeserializer : JsonDeserializer<ValueClassFoo>() {
    override fun deserialize(jsonParser: JsonParser, ctxt: DeserializationContext?): ValueClassFoo {
        val node = jsonParser.codec.readTree<JsonNode>(jsonParser)
        return ValueClassFoo(
            fizz = node[ValueClassFoo.FIZZ_FIELD].asText(),
            bizz = node[ValueClassFoo.BIZZ_FIELD].asInt().toUInt(),
            bazz = (node[ValueClassFoo.BAZZ_FIELD] as ArrayNode).map { it.textValue() }
        )
    }
}

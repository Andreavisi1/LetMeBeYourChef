// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")

package com.example.letmebeyourchef.model.Json_Parsing

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.NullPointerException
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.text.buildString

public class Json_LinksJsonAdapter(
  moshi: Moshi
) : JsonAdapter<Json_Links>() {
  private val options: JsonReader.Options = JsonReader.Options.of("next")

  private val nullableJson_NextAdapter: JsonAdapter<Json_Next?> =
      moshi.adapter(Json_Next::class.java, emptySet(), "next")

  public override fun toString(): String = buildString(32) {
      append("GeneratedJsonAdapter(").append("Json_Links").append(')') }

  public override fun fromJson(reader: JsonReader): Json_Links {
    var next: Json_Next? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> next = nullableJson_NextAdapter.fromJson(reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return Json_Links(
        next = next
    )
  }

  public override fun toJson(writer: JsonWriter, value_: Json_Links?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("next")
    nullableJson_NextAdapter.toJson(writer, value_.next)
    writer.endObject()
  }
}

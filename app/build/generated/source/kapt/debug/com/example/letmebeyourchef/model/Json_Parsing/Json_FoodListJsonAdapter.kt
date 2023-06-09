// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")

package com.example.letmebeyourchef.model.Json_Parsing

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.NullPointerException
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.emptySet
import kotlin.text.buildString

public class Json_FoodListJsonAdapter(
  moshi: Moshi
) : JsonAdapter<Json_FoodList>() {
  private val options: JsonReader.Options = JsonReader.Options.of("hints", "_links")

  private val nullableListOfJson_HintAdapter: JsonAdapter<List<Json_Hint>?> =
      moshi.adapter(Types.newParameterizedType(List::class.java, Json_Hint::class.java), emptySet(),
      "hints")

  private val nullableJson_LinksAdapter: JsonAdapter<Json_Links?> =
      moshi.adapter(Json_Links::class.java, emptySet(), "_links")

  public override fun toString(): String = buildString(35) {
      append("GeneratedJsonAdapter(").append("Json_FoodList").append(')') }

  public override fun fromJson(reader: JsonReader): Json_FoodList {
    var hints: List<Json_Hint>? = null
    var _links: Json_Links? = null
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> hints = nullableListOfJson_HintAdapter.fromJson(reader)
        1 -> _links = nullableJson_LinksAdapter.fromJson(reader)
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    return Json_FoodList(
        hints = hints,
        _links = _links
    )
  }

  public override fun toJson(writer: JsonWriter, value_: Json_FoodList?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("hints")
    nullableListOfJson_HintAdapter.toJson(writer, value_.hints)
    writer.name("_links")
    nullableJson_LinksAdapter.toJson(writer, value_._links)
    writer.endObject()
  }
}

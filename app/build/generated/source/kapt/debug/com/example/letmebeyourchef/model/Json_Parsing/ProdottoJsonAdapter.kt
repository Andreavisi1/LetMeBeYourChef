// Code generated by moshi-kotlin-codegen. Do not edit.
@file:Suppress("DEPRECATION", "unused", "ClassName", "REDUNDANT_PROJECTION",
    "RedundantExplicitType", "LocalVariableName", "RedundantVisibilityModifier",
    "PLATFORM_CLASS_MAPPED_TO_KOTLIN", "IMPLICIT_NOTHING_TYPE_ARGUMENT_IN_RETURN_POSITION")

package com.example.letmebeyourchef.model.Json_Parsing

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.`internal`.Util
import java.lang.NullPointerException
import java.lang.reflect.Constructor
import kotlin.Int
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.emptySet
import kotlin.jvm.Volatile
import kotlin.text.buildString

public class ProdottoJsonAdapter(
  moshi: Moshi
) : JsonAdapter<Prodotto>() {
  private val options: JsonReader.Options = JsonReader.Options.of("brand", "category",
      "categoryLabel", "foodContentsLabel", "foodId", "image", "knownAs", "label", "nutrients")

  private val nullableStringAdapter: JsonAdapter<String?> = moshi.adapter(String::class.java,
      emptySet(), "brand")

  private val nullableNutrientsAdapter: JsonAdapter<Nutrients?> =
      moshi.adapter(Nutrients::class.java, emptySet(), "nutrients")

  @Volatile
  private var constructorRef: Constructor<Prodotto>? = null

  public override fun toString(): String = buildString(30) {
      append("GeneratedJsonAdapter(").append("Prodotto").append(')') }

  public override fun fromJson(reader: JsonReader): Prodotto {
    var brand: String? = null
    var category: String? = null
    var categoryLabel: String? = null
    var foodContentsLabel: String? = null
    var foodId: String? = null
    var image: String? = null
    var knownAs: String? = null
    var label: String? = null
    var nutrients: Nutrients? = null
    var mask0 = -1
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> {
          brand = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 0).inv()
          mask0 = mask0 and 0xfffffffe.toInt()
        }
        1 -> {
          category = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 1).inv()
          mask0 = mask0 and 0xfffffffd.toInt()
        }
        2 -> {
          categoryLabel = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 2).inv()
          mask0 = mask0 and 0xfffffffb.toInt()
        }
        3 -> {
          foodContentsLabel = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 3).inv()
          mask0 = mask0 and 0xfffffff7.toInt()
        }
        4 -> {
          foodId = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 4).inv()
          mask0 = mask0 and 0xffffffef.toInt()
        }
        5 -> {
          image = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 5).inv()
          mask0 = mask0 and 0xffffffdf.toInt()
        }
        6 -> {
          knownAs = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 6).inv()
          mask0 = mask0 and 0xffffffbf.toInt()
        }
        7 -> {
          label = nullableStringAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 7).inv()
          mask0 = mask0 and 0xffffff7f.toInt()
        }
        8 -> {
          nutrients = nullableNutrientsAdapter.fromJson(reader)
          // $mask = $mask and (1 shl 8).inv()
          mask0 = mask0 and 0xfffffeff.toInt()
        }
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    if (mask0 == 0xfffffe00.toInt()) {
      // All parameters with defaults are set, invoke the constructor directly
      return  Prodotto(
          brand = brand,
          category = category,
          categoryLabel = categoryLabel,
          foodContentsLabel = foodContentsLabel,
          foodId = foodId,
          image = image,
          knownAs = knownAs,
          label = label,
          nutrients = nutrients
      )
    } else {
      // Reflectively invoke the synthetic defaults constructor
      @Suppress("UNCHECKED_CAST")
      val localConstructor: Constructor<Prodotto> = this.constructorRef ?:
          Prodotto::class.java.getDeclaredConstructor(String::class.java, String::class.java,
          String::class.java, String::class.java, String::class.java, String::class.java,
          String::class.java, String::class.java, Nutrients::class.java,
          Int::class.javaPrimitiveType, Util.DEFAULT_CONSTRUCTOR_MARKER).also {
          this.constructorRef = it }
      return localConstructor.newInstance(
          brand,
          category,
          categoryLabel,
          foodContentsLabel,
          foodId,
          image,
          knownAs,
          label,
          nutrients,
          mask0,
          /* DefaultConstructorMarker */ null
      )
    }
  }

  public override fun toJson(writer: JsonWriter, value_: Prodotto?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("brand")
    nullableStringAdapter.toJson(writer, value_.brand)
    writer.name("category")
    nullableStringAdapter.toJson(writer, value_.category)
    writer.name("categoryLabel")
    nullableStringAdapter.toJson(writer, value_.categoryLabel)
    writer.name("foodContentsLabel")
    nullableStringAdapter.toJson(writer, value_.foodContentsLabel)
    writer.name("foodId")
    nullableStringAdapter.toJson(writer, value_.foodId)
    writer.name("image")
    nullableStringAdapter.toJson(writer, value_.image)
    writer.name("knownAs")
    nullableStringAdapter.toJson(writer, value_.knownAs)
    writer.name("label")
    nullableStringAdapter.toJson(writer, value_.label)
    writer.name("nutrients")
    nullableNutrientsAdapter.toJson(writer, value_.nutrients)
    writer.endObject()
  }
}

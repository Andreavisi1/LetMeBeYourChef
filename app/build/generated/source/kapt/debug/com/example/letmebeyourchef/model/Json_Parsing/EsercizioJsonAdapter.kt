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

public class EsercizioJsonAdapter(
  moshi: Moshi
) : JsonAdapter<Esercizio>() {
  private val options: JsonReader.Options = JsonReader.Options.of("id", "calories_per_hour",
      "duration_minutes", "name", "total_calories")

  private val stringAdapter: JsonAdapter<String> = moshi.adapter(String::class.java, emptySet(),
      "id")

  private val intAdapter: JsonAdapter<Int> = moshi.adapter(Int::class.java, emptySet(),
      "calorieOra")

  @Volatile
  private var constructorRef: Constructor<Esercizio>? = null

  public override fun toString(): String = buildString(31) {
      append("GeneratedJsonAdapter(").append("Esercizio").append(')') }

  public override fun fromJson(reader: JsonReader): Esercizio {
    var id: String? = null
    var calorieOra: Int? = 0
    var durata: Int? = 0
    var nome: String? = null
    var calorieTot: Int? = 0
    var mask0 = -1
    reader.beginObject()
    while (reader.hasNext()) {
      when (reader.selectName(options)) {
        0 -> {
          id = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("id", "id", reader)
          // $mask = $mask and (1 shl 0).inv()
          mask0 = mask0 and 0xfffffffe.toInt()
        }
        1 -> {
          calorieOra = intAdapter.fromJson(reader) ?: throw Util.unexpectedNull("calorieOra",
              "calories_per_hour", reader)
          // $mask = $mask and (1 shl 1).inv()
          mask0 = mask0 and 0xfffffffd.toInt()
        }
        2 -> {
          durata = intAdapter.fromJson(reader) ?: throw Util.unexpectedNull("durata",
              "duration_minutes", reader)
          // $mask = $mask and (1 shl 2).inv()
          mask0 = mask0 and 0xfffffffb.toInt()
        }
        3 -> {
          nome = stringAdapter.fromJson(reader) ?: throw Util.unexpectedNull("nome", "name", reader)
          // $mask = $mask and (1 shl 3).inv()
          mask0 = mask0 and 0xfffffff7.toInt()
        }
        4 -> {
          calorieTot = intAdapter.fromJson(reader) ?: throw Util.unexpectedNull("calorieTot",
              "total_calories", reader)
          // $mask = $mask and (1 shl 4).inv()
          mask0 = mask0 and 0xffffffef.toInt()
        }
        -1 -> {
          // Unknown name, skip it.
          reader.skipName()
          reader.skipValue()
        }
      }
    }
    reader.endObject()
    if (mask0 == 0xffffffe0.toInt()) {
      // All parameters with defaults are set, invoke the constructor directly
      return  Esercizio(
          id = id as String,
          calorieOra = calorieOra as Int,
          durata = durata as Int,
          nome = nome as String,
          calorieTot = calorieTot as Int
      )
    } else {
      // Reflectively invoke the synthetic defaults constructor
      @Suppress("UNCHECKED_CAST")
      val localConstructor: Constructor<Esercizio> = this.constructorRef ?:
          Esercizio::class.java.getDeclaredConstructor(String::class.java,
          Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, String::class.java,
          Int::class.javaPrimitiveType, Int::class.javaPrimitiveType,
          Util.DEFAULT_CONSTRUCTOR_MARKER).also { this.constructorRef = it }
      return localConstructor.newInstance(
          id,
          calorieOra,
          durata,
          nome,
          calorieTot,
          mask0,
          /* DefaultConstructorMarker */ null
      )
    }
  }

  public override fun toJson(writer: JsonWriter, value_: Esercizio?): Unit {
    if (value_ == null) {
      throw NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.")
    }
    writer.beginObject()
    writer.name("id")
    stringAdapter.toJson(writer, value_.id)
    writer.name("calories_per_hour")
    intAdapter.toJson(writer, value_.calorieOra)
    writer.name("duration_minutes")
    intAdapter.toJson(writer, value_.durata)
    writer.name("name")
    stringAdapter.toJson(writer, value_.nome)
    writer.name("total_calories")
    intAdapter.toJson(writer, value_.calorieTot)
    writer.endObject()
  }
}
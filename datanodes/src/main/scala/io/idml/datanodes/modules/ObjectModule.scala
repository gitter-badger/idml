package io.idml.datanodes.modules

import io.idml.datanodes.{PArray, PString}
import io.idml._

import scala.util.Try

/** Adds object-like behaviour */
trait ObjectModule {
  this: PtolemyValue =>

  /** Remove a field by name */
  def remove(path: String) {}

  def serialize(): PtolemyValue = this match {
    case o: PtolemyObject => PtolemyValue(PtolemyJson.compact(o))
    case _                => InvalidCaller
  }

  def values(): PtolemyValue = this match {
    case o: PtolemyObject => PArray(o.fields.values.toBuffer)
    case _                => InvalidCaller
  }

  def keys(): PtolemyValue = this match {
    case o: PtolemyObject => PArray(o.fields.keys.map(PString.apply).toBuffer[PtolemyValue])
    case _                => InvalidCaller
  }

  def parseJson(): PtolemyValue = this match {
    case s: PtolemyString => Try { PtolemyJson.parse(s.value) }.getOrElse(InvalidCaller)
    case _                => InvalidCaller
  }
}

package com.github.thekana.aoc

import scala.language.implicitConversions

package object y2021 {
  implicit def intTransformer(x: String): Int = java.lang.Integer.parseInt(x)

  implicit def stringIntTuple2Transformer(x: String): (String, Int) = {
    val pair = x.split(' ')
    require(pair.length == 2, s"expected length after split 2 got ${pair.length}")
    pair match {
      case Array(t1: String, t2: String, _*) => (t1, java.lang.Integer.parseInt(t2))
    }
  }
}

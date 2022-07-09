package com.github.thekana.aoc
package utils

import scala.io.{BufferedSource, Source}

trait InputReader {
  def usingInput[A, B](f: Seq[B] => A)(implicit filePath: String, parser: Parser[B]): A = {
    val source: BufferedSource = Source.fromFile(filePath)
    val input                  = source.getLines.map(parser.parse).toSeq
    source.close()
    f(input)
  }

  def withSourceFile[T](filePath: String)(f: BufferedSource => T): T = {
    val source: BufferedSource = Source.fromFile(filePath)
    val result                 = f(source)
    source.close()
    result
  }

}

trait Parser[T] {
  def parse(value: String): T
}

object Parser {
  implicit object StringParser extends Parser[String] {
    override def parse(value: String): String = value
  }

  implicit object IntParser extends Parser[Int] {
    override def parse(value: String): Int = value.toInt
  }

  implicit object StringIntTuple2Parser extends Parser[(String, Int)] {
    override def parse(value: String): (String, Int) = {
      val pair = value.split(' ')
      require(pair.length == 2, s"expected length 2 after split, but got ${pair.length}")
      pair match {
        case Array(t1: String, t2: String, _*) => (t1, t2.toInt)
      }
    }
  }
}

package com.github.thekana.aoc
package utils

import scala.io.{BufferedSource, Source}

trait InputReader {
  def usingInput[A, B](f: Seq[B] => A)(implicit filePath: String, transform: String => B): A = {
    val source: BufferedSource = Source.fromFile(filePath)
    val input = source.getLines().toSeq.map(transform)
    source.close()
    f(input)
  }
}
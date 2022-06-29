package com.github.thekana.aoc
package y2021

import org.scalatest.flatspec.AnyFlatSpec

import scala.io.{BufferedSource, Source}

class Day1Test extends AnyFlatSpec {

  trait TestInput {
    val source: BufferedSource = Source.fromFile("src/test/resources/day1/input.txt")
    val input: Seq[Int] = source.getLines().toSeq.map(_.toInt)
    source.close()
  }

  "part1" should "be correct" in new TestInput {
    assert(Day1.part1(input) == 1521)
  }

  "part2" should "be correct" in new TestInput {
    assert(Day1.part2(input) == 1543)
  }
}

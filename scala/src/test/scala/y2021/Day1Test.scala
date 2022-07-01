package com.github.thekana.aoc
package y2021

import utils.InputReader

import org.scalatest.flatspec.AnyFlatSpec

class Day1Test extends AnyFlatSpec with InputReader {

  implicit val filePath: String = "src/test/resources/day1/input.txt"

  "part1" should "be correct" in {
    assert(usingInput(Day1.part1) == 1521)
  }

  "part2" should "be correct" in {
    assert(usingInput(Day1.part2) == 1543)
  }
}

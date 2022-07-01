package com.github.thekana.aoc
package y2021

import utils.InputReader

import org.scalatest.flatspec.AnyFlatSpec

class Day3Test extends AnyFlatSpec with InputReader {

  implicit val filePath: String = "src/test/resources/day3/input.txt"

  "part1" should "be correct" in {
    assert(usingInput(Day3.part1) == 3242606)
  }

  "part2" should "be correct" in {
    assert(usingInput(Day3.part2) == 4856080)
  }

}

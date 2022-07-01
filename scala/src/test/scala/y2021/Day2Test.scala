package com.github.thekana.aoc
package y2021

import utils.InputReader

import org.scalatest.flatspec.AnyFlatSpec

class Day2Test extends AnyFlatSpec with InputReader {

  implicit val filePath: String = "src/test/resources/day2/input.txt"

  "part1" should "be correct" in {
    assert(usingInput(Day2.part1) == 1636725)
  }

  "part2" should "be correct" in {
    assert(usingInput(Day2.part2) == 1872757425)
  }
}

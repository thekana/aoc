package com.github.thekana.aoc
package y2021

import utils.InputReader

import org.scalatest.flatspec.AnyFlatSpec

class Day4Test extends AnyFlatSpec with InputReader {

  val exampleFilePath: String = "src/test/resources/day4/example.txt"
  val puzzleFilePath: String  = "src/test/resources/day4/input.txt"

  trait BingoDataScope {
    val filePath: String = ""

    def numbersDrawn: List[Int] = withSourceFile(filePath) { f =>
      f.getLines().toList.head.split(',').map(_.toInt).toList
    }

    def boards: List[List[Int]] = withSourceFile(filePath) { f =>
      f.getLines().toList.tail.filterNot(_.isBlank).grouped(5).toList
    }.map(_.flatMap(_.split(' ').filterNot(_.isBlank).map(_.toInt)))
  }

  "part1" should "example" in new BingoDataScope {
    override val filePath: String = exampleFilePath
    assert(Day4.part1(numbersDrawn, boards) == 4512)
  }

  "part1" should "puzzle" in new BingoDataScope {
    override val filePath: String = puzzleFilePath
    assert(Day4.part1(numbersDrawn, boards) == 29440)
  }
}

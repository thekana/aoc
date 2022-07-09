package com.github.thekana.aoc
package y2021

/**
 * https://adventofcode.com/2021/day/1
 */
object Day1 {

  val windowSize = 3

  def part2(input: Seq[Int]): Int = {
    def slidingWindowSum: Seq[Int] =
      input.sliding(windowSize).map(window => window.sum).toSeq

    part1(slidingWindowSum)
  }

  def part1(input: Seq[Int]): Int =
    input
      .foldLeft[(Int, Option[Int])]((0, None)) { (acc, current) =>
        val result = acc._2 match {
          case Some(value) if value < current => 1
          case _                              => 0
        }
        (acc._1 + result, Some(current))
      }
      ._1
}

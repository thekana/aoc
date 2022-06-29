package com.github.thekana.aoc
package y2021

/**
 * https://adventofcode.com/2021/day/1
 */
object Day1 {

  def part1(input: Seq[Int]): Int = {
    input.foldLeft[(Int, Option[Int])]((0, None)) {
      (acc, current) => {
        acc._2 match {
          case Some(value) => if (value < current) {
            (acc._1 + 1, Some(current))
          } else {
            (acc._1, Some(current))
          }
          case _ => (acc._1, Some(current))
        }
      }
    }._1
  }

  val windowSize = 3

  def part2(input: Seq[Int]): Int = {
    def slidingWindowSum: Seq[Int] = {
      input.sliding(windowSize).map(window => window.sum).toSeq
    }

    part1(slidingWindowSum)
  }
}

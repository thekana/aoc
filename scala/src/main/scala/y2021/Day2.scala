package com.github.thekana.aoc
package y2021

/**
 * https://adventofcode.com/2021/day/2
 */
object Day2 {
  def part1(commands: Seq[(String, Int)]): Int = {
    val finalPost = commands.foldLeft[(Int, Int)](0, 0) { (position, command) =>
      command match {
        case ("forward", x) => (position._1 + x, position._2)
        case ("down", y)    => (position._1, position._2 + y)
        case ("up", y)      => (position._1, position._2 - y)
        case _              => throw new IllegalArgumentException(command.toString())
      }
    }
    finalPost._1 * finalPost._2
  }

  def part2(commands: Seq[(String, Int)]): Int = {

    def calculateDepthChange(forward: Int, aim: Int): Int =
      if (aim <= 0) {
        0
      } else {
        forward * aim
      }

    val finalPost = commands.foldLeft[(Int, Int, Int)](0, 0, 0) { (positionWithAim, command) =>
      command match {
        case ("forward", x) =>
          (positionWithAim._1 + x, positionWithAim._2 + calculateDepthChange(x, positionWithAim._3), positionWithAim._3)
        case ("down", y) => (positionWithAim._1, positionWithAim._2, positionWithAim._3 + y)
        case ("up", y)   => (positionWithAim._1, positionWithAim._2, positionWithAim._3 - y)
        case _           => throw new IllegalArgumentException(command.toString())
      }
    }
    finalPost._1 * finalPost._2
  }
}

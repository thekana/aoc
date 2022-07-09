package com.github.thekana.aoc
package y2021

import scala.Console.{BOLD, RESET, UNDERLINED}

object Day4 {

  def part1(numbersDrawn: List[Int], boards: List[List[Int]]): Int = {

    var bingoBoards = boards.map(BingoBoard(_))

    for (numberDrawn <- numbersDrawn) {
//      println(s"Number Drawn $numberDrawn")
      val nextState = bingoBoards.map(_.mark(numberDrawn))
//      nextState.foreach(_.print())
      bingoBoards = nextState
      val winner = nextState.find(_.getWinner.nonEmpty)
      if (winner.nonEmpty) {
        return winner.get.sumOfUnmarkedNumbers * numberDrawn
      }
    }
    throw new IllegalStateException("no winner")
  }

  def part2(numbersDrawn: List[Int], boards: List[List[Int]]): Int = {

    var bingoBoards = boards.map(BingoBoard(_))

    for (numberDrawn <- numbersDrawn) {
//      println(s"Number Drawn $numberDrawn")

      val nextState = bingoBoards.map(_.mark(numberDrawn))
//      nextState.foreach(_.print())

      bingoBoards = nextState
      val winners = nextState.filter(_.getWinner.nonEmpty)
      winners.foreach { winner =>
        // check if it's the last winner
        if (bingoBoards.length == 1) {
          return winner.sumOfUnmarkedNumbers * numberDrawn
        }
        // drop winner
        bingoBoards = bingoBoards.filterNot(_ == winner)
      }
    }
    throw new IllegalStateException("no winner")
  }

  private case class Bingo(number: Int, marked: Boolean = false) {
    override def toString: String = {
      val numberStr = if (number < 10) " " + number.toString else number.toString
      if (marked) {
        BOLD + UNDERLINED + numberStr
      } else {
        numberStr
      }
    }
  }

  private case class Board(currentState: List[Bingo], sumOfUnmarkedNumbers: Int, size: Int = 5) {
    private val rowMask: List[List[Int]] = (0 until size * size)
      .map(identity)
      .toList
      .grouped(size)
      .toList
    private val colMask: List[List[Int]] = (0 until size)
      .map(i => List.range(i, size * size, size))
      .toList

    def getWinner: Option[List[Int]] =
      rowMask
        .map(mask => mask.map(currentState(_)))
        .find(_.forall(_.marked))
        .orElse(colMask.map(mask => mask.map(currentState(_))).find(_.forall(_.marked)))
        .map(_.map(_.number))

    def mark(numberToMark: Int): Board = {
      val indicesToMark = currentState.zipWithIndex.flatMap { case (bingo, i) =>
        if (bingo.number == numberToMark && !bingo.marked) {
          Some(i)
        } else {
          None
        }
      }
      val nextState = indicesToMark.foldLeft[List[Bingo]](this.currentState) { (b, i) =>
        b.updated(i, b(i).copy(marked = true))
      }
      Board(
        nextState,
        sumOfUnmarkedNumbers = sumOfUnmarkedNumbers - indicesToMark.length * numberToMark
      )
    }

    def print(): Unit = {
      this.currentState.indices.foreach {
        case i if (i + 1) % 5 == 0 => println(RESET + " " + this.currentState(i))
        case i: Int => printf(RESET + " " + this.currentState(i))
      }
      println()
    }
  }

  private object BingoBoard {
    def apply(numbersOnBoard: List[Int]): Board =
      Board(numbersOnBoard.map(Bingo(_)), numbersOnBoard.sum)
  }
}

package com.github.thekana.aoc
package y2021

import scala.annotation.tailrec

/**
 * https://adventofcode.com/2021/day/3
 * TODO: Better binary representation
 * This implementation is confusing AF
 */
object Day3 {

  case class Binary(value: Int, zeroes: Int, ones: Int) {
    lazy val bit: Int = if (value > 0) 1 else 0
    lazy val mostCommon: Int = if (ones > zeroes) 1 else 0
    lazy val leastCommon: Int = if (ones > zeroes) 0 else 1
  }

  case class BinaryReport(bits: Seq[Binary]) {

    lazy val length: Int = bits.length

    def merge(other: BinaryReport): BinaryReport = {
      require(this.length == other.length)
      BinaryReport(this.bits.zip(other.bits).map {
        case (a, b) => Binary(a.value + b.value, a.zeroes + b.zeroes, a.ones + b.ones)
      })
    }

    def gamma: Int = bits.map(_.mostCommon).foldLeft((0D, length - 1)) {
      (acc, d) => (acc._1 + (d * math.pow(2, acc._2)), acc._2 - 1)
    }._1.toInt

    def epsilon: Int = bits.map(_.leastCommon).foldLeft((0D, length - 1)) {
      (acc, d) => (acc._1 + (d * math.pow(2, acc._2)), acc._2 - 1)
    }._1.toInt

    def decimal: Int = bits.map(_.bit).foldLeft((0D, length - 1)) {
      (acc, d) => (acc._1 + (d * math.pow(2, acc._2)), acc._2 - 1)
    }._1.toInt
  }

  def part1(bits: Seq[String]): Int = {

    val binaryReport = bits.map(b => {
      BinaryReport(b.map {
        case '1' => Binary(1, 0, 1)
        case '0' => Binary(0, 1, 0)
      })
    }).reduceLeft { (a, b) => a.merge(b) }

    binaryReport.gamma * binaryReport.epsilon
  }

  @tailrec
  def oxygen(reports: Seq[BinaryReport], pos: Int): BinaryReport = {
    if (reports.length == 1) {
      reports.head
    } else {
      val merged = reports.reduceLeft((a, b) => a.merge(b))
      val mostCommonBitAtPos = if (merged.bits(pos).ones >= merged.bits(pos).zeroes) 1 else 0
      oxygen(reports.filter(r => r.bits(pos).value == mostCommonBitAtPos), pos + 1)
    }
  }


  @tailrec
  def co2(reports: Seq[BinaryReport], pos: Int): BinaryReport = {
    if (reports.length == 1) {
      reports.head
    } else {
      val merged = reports.reduceLeft((a, b) => a.merge(b))
      val mostCommonBitAtPos = if (merged.bits(pos).ones >= merged.bits(pos).zeroes) 0 else 1
      co2(reports.filter(r => r.bits(pos).value == mostCommonBitAtPos), pos + 1)
    }
  }

  def part2(bits: Seq[String]): Int = {

    val binaryReports = bits.map(b => {
      BinaryReport(b.map {
        case '1' => Binary(1, 0, 1)
        case '0' => Binary(0, 1, 0)
      })
    })

    val oxygen = this.oxygen(binaryReports, 0)
    val co2 = this.co2(binaryReports, 0)

    oxygen.decimal * co2.decimal
  }
}

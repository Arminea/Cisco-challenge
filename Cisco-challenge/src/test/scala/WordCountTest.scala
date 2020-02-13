package test.scala

import main.scala.WordCount
import org.junit.runner.RunWith
import org.scalatest.{FlatSpec, Matchers}
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class WordCountTest extends FlatSpec with Matchers {

  it should "read file correctly" in {

    val fileContentExpected = "mary had a little lamb little lamb little lamb mary had a little lamb its fleece was white as snow and every where that mary went mary went mary went"

    val filePath = getClass.getResource("/mary.txt").getPath
    val fileContentActual = WordCount.readInputFile(filePath)

    fileContentActual shouldBe fileContentExpected
  }

  it should "count correctly" in {

    val content = "abc bca cba bca abc cab"

    val workContentExpected = Map(
      "abc" -> 2,
      "bca" -> 2,
      "cba" -> 1,
      "cab" -> 1
    )

    val wordCountActual = WordCount.countWordsOccurrences(content)

    wordCountActual shouldBe workContentExpected
  }

}

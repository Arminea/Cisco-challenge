package main.scala

object WordCountRunner extends App {

  run()

  def run() = {

    val filePath = getClass.getResource("/rainbow.txt").getPath
    val fileContent = WordCount.readInputFile(filePath)

    val occurrences = WordCount.countWordsOccurrences(fileContent)

    println(occurrences)

  }

}

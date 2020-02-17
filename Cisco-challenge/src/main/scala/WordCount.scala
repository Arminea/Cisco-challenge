import scala.io.Source

object WordCount {

  /**
   * Function reads a file from input path.
   *
   * @param filepath
   * @return file content as one line string
   */
  def readInputFile(filepath: String): String = {

    def normalizeContent(content: String): String = {
      content.toLowerCase.replaceAll("[.,?!]", "")
    }

    val fileContent = Source.fromFile(filepath).getLines.mkString(" ")

    normalizeContent(fileContent)
  }

  /**
   * File content is split, grouped and mapped.
   *
   * @param fileContent
   * @return immutable map of word and their occurrences
   */
  def countWordsOccurrences(fileContent: String): Map[String, Int] = {

    fileContent.split(" ").groupBy(identity).mapValues(_.size)

  }

}
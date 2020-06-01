// file operations:
// 1.listing all files in a directory
// 2.printing file content by formatting them
// 3.simulating grep
// 4.error handling for file not found functiom

import java.io.{File, FileNotFoundException}

import scala.io.Source

def allFiles() = {
  val filesList = (new File(".")).listFiles()
  for(file <- filesList)
    println(file)
}

def grep(pattern: String) = {
  val filesList = (new File("./src")).listFiles()
  for(
    file <- filesList
    if file.getName.endsWith(".txt");
    line <- Source.fromFile("src/"+file.getName).getLines().toList
    if line.trim.matches(pattern)
  ) println(file + ":" + line.trim)
}

allFiles()
print("grep function ")
grep(".*h.*")
def lineWidth(s: String) = s.length.toString.length
if(args.length> 0) {
  try {
    val lines = Source.fromFile(args(0)).getLines().toList
    val maxLengthLine = lines.reduceLeft(
      (a, b) => if (a.length > b.length) a else b
    )
    val maxLen = lineWidth(maxLengthLine)
    for (line <- lines) {
      val numSpaces = maxLen - lineWidth(line)
      val padding = " " * numSpaces
      println(padding + line.length + " | " + line)

    }
  }
  catch {
    case ex: FileNotFoundException => println("file  not found")
  }
}

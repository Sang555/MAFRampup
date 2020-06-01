// File matcher showing control abstract
// 1.create a fileMatcher that takes in the matching function as a value
// it reduces duplicate code i.e. instead of repeating code in many functions

object fileMatcher {
  private def listFiles = (new java.io.File(".")).listFiles()
  // used only by  functions in this object..this is the redudant code
  private def matchingFunction(matcher: String => Boolean) = {
    for (file <- listFiles; if matcher(file.getName))
      yield file
  }

  def findFilesEndingWith(name: String)=
    matchingFunction(_.endsWith(name))
  def findFilesContaining(name: String)=
    matchingFunction(_.contains(name))
}

for(file <- fileMatcher.findFilesEndingWith("rc")) {
  println(file)
}
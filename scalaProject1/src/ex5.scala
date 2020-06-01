// understanding yield
//understanding currying
// 1.printing 1 to 10 tables in a matrix
// 2. transform add to a currying function


def makeEachRow(row: Int) = {
  for (col <- 1 to 10) yield {
    val prod = (row * col).toString
    val padding = " " * (4 - prod.length)
    padding + prod
}
}
def makeRow(row: Int) = makeEachRow(row).mkString
  def makeTable() = {
    val table =
      for (row <- 1 to 10)
        yield makeRow(row)
    table.mkString("\n")
  }
println(makeTable())

object Curry {
  def add2(a: Int) = (b: Int) => a + b;
}
println(Curry.add2(20)(19));

import cats.Eval
// vals are eager..immediate Eval.now
// def are lazy ...later but not tsored in cache i.e. recomputed Eval.always
// lazy vals are lazy and memoised...EVal.later
//eval monads flatMap and map functions are added as computations to chain..which are executed later
//it help is prevent stcakoverflow  by chainig all ooperations and using internal trampoline
object EvalMonad extends App {
  val x = {
    println("Computing X")
    math.random
  }

  def y = {
    println("Computing Y")
    math.random
  }

  lazy val z = {
    println("Computing Z")
    math.random
  }


  println(y)
  println(y)
  println(z)
  println(z)
  val now = Eval.now(math.random + 1000)
  val later = Eval.later(math.random + 2000)
  val always = Eval.always(math.random + 3000)
  println(now.value)
  println(later.value)
  println(always.value)


  def factorial(n: BigInt): Eval[BigInt] =
    if (n == 1) {
      Eval.now(n)
    } else {
      Eval.defer(factorial(n - 1).map(_ * n))
    }

  //defer does trampolining by storing object sin heap instead of consuming stack so wont throw stack overflowx
  factorial(50000).value
}
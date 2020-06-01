import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._

// understanding different types of functors
// List, Option, Either
// create own functor
// they are type constructors using a mapfunction

object FunctorsDemo {
  //1. List
  List(1, 2, 3).map(n => n + 1).map(n => n * 2).map(n => s"${n}!")

  //2. Future is a functor that applies async operations , but we have no idea on the internal state..like ongoing, complete, rejected
  // Await.result waits for the async function for mentioned time period

  val future: Future[String] =
    Future(123).map(n => n + 1).map(n => n * 2).map(n => (s"{$n"))

  Await.result(future, 1.second)

  //3. Single Argument Functions, performs lazy evaluationw hen argument  is supplied
//  val func = ((x: Int) => x.toDouble).map(x => x + 1).map(x => x * 2).map(x => s"{$x")
//  func(123)

}

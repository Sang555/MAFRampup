import cats.syntax.either._
//define an EIther
//it is a monad because ins eries of computations it decides whether too coontinue further comoputation or handle error
object EitherMonad extends App {
  val ok: Either[Error, String] =
    Right("This is right")
  val error: Either[Error, String] =
    Left(new Error("Thats an error!"))

  def uncertainComputation(x: Float): Either[Error, String] =
    if (x > 0.5)
      Right("Thats right")
    else
      Left(new Error("THats an error"))

  println(uncertainComputation(0.6f))

  val a = 3.asRight[String]
  val b = 4.asRight[String]

  //defining a function to count positive..error is returned as Left
  def countPositive(nums: List[Int]) = nums.foldLeft(0.asRight[String]) { (accumulator, num) =>
    if (num > 0) {
      accumulator.map(_ + 1)
    } else {
      Left("Negative. Stopping!")
    }
  }

  println(countPositive(List(1, 2, 3)))
  println(countPositive(List(1, -2, 3)))
}
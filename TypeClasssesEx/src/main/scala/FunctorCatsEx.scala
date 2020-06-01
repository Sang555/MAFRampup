import cats.Functor
import cats.instances.list._
import cats.instances.option._
import cats.instances.function._
import cats.syntax.functor._

// understanding cats functors
object CatsFuncDemo extends App {
  val l1 = List(1, 2, 3)
  //functor takes F[_] type and has map functino
  val l2 = Functor[List].map(l1)(_ * 2)

  val op1 = Option(123)
  val op2 = Functor[Option].map(op1)(_.toString)

  // it has a lift function using which can convert function of A=>B to F[A]=>F[B]
  val function1 = (x: Int) => x + 1
  val liftedFunction = Functor[Option].lift(function1)
  println(liftedFunction(Option(1)))
  println(liftedFunction(None))

  //function functors in cats
  val f1 = (a: Int) => a + 1
  val f2 = (a: Int) => a * 2
  val f3 = (a: Int) => s"${a}"
  val f4 = f1.map(f2).map(f3)
  println(f4(1))

  //abstract functor that can be used for anytype
  //this like type class interface
  def doCalc[F[_]](start: F[Int])
                  (implicit  functor: Functor[F]): F[Int] =
    start.map(n=> n+2)

  println(doCalc((Option(1))))
  println(doCalc(List(1,2,3)))

    //for any other class type, for which the implcit functor instance is not defined, will throw error





}


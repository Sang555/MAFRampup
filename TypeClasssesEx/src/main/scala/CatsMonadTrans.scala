import cats.data.OptionT
import cats.data.EitherT
//monad transformers are available in cats.data
import cats.instances.list._
import cats.syntax.applicative._
import cats.instances.either._
import scala.concurrent.Future

//ToDo:
// 1.build monad stacks using transformers
// 2.construct instances of monad stack
// 3.pull apart stack to access the monads using value


object catsMonadTrans extends App {
  //here OptionT is a monad given by cat and it is the inner monoid
  //the outer monad is passed as the parameter
  type ListOption[A] = OptionT[List, A]

  //can create instacnes of ListOption using constructor or pure
  val r1 = OptionT(List(Option(10))) //i.e.apply method
  println(r1)
  val r2 = 10.pure[ListOption]
  println(r2)

println(r1.flatMap { (x:Int) =>
  r2.map { (y:Int) => x+y }
})

  type ErrorOr[A] = Either[String, A]
  type ErrorOrOption[A] = OptionT[ErrorOr, A]
  val a = 10.pure[ErrorOrOption]
  val b = 32.pure[ErrorOrOption]
  val c = a.flatMap(x => b.map(y => x + y))
  println(c)
  println(a)
  println(a.value)
  val a2 = a.value

  //each call of value on the monad stack unpacks one context and define map methods
  sealed abstract class HttpError
  final case class NotFound(item: String) extends HttpError
  final case class BadRequest(msg: String) extends HttpError // etc...
  type FutureEither[A] = EitherT[Future, HttpError, A] //i.e.Future[]
}
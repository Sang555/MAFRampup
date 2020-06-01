import cats.Functor
import cats.instances.list._
import cats.instances.option._
import cats.instances.function._
import cats.syntax.functor._


object CustomFunctor extends App {

  trait Functor[F[_]] {
    def map[A,B](fa: F[A])(f: A=> B): F[B]
  }
  case class Bag[A](value: A)
object Functor {
  def apply[F[_]](implicit M: Functor[F]): Functor[F] = M



  //defining our own  functor for custom type, here bag is a type constructor
  implicit val bagFunctor: Functor[Bag] =
    new Functor[Bag] {
      def map[A, B](fa: Bag[A])(f: A => B): Bag[B] = Bag(f(fa.value))
    }
}
 val bag = new Bag(1)
  val b = Functor[Bag]
  def f = (a:Int)=> a+1

  println(b.map(bag)(n => n + 1))
}
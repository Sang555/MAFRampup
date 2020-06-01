import cats.Functor
import cats.instances.list._
import cats.instances.option._
import cats.instances.function._
import cats.syntax.functor._


object CustomMonad extends App {

  case class Bag[A](value: A)
  trait Monad[M[_]] {
    def pure[A](a: A): M[A]

    def flatMap[A, B](ma: M[A])(f: A => M[B]): M[B]
  }

  object Monad {
    def apply[F[_]](implicit M: Monad[F]): Monad[F] = M

    //defining our own  functor for custom type, here bag is a type constructor
    implicit val bagMonad: Monad[Bag] =
      new Monad[Bag] {
        def flatMap[A, B](fa: Bag[A])(f: A => Bag[B]): Bag[B] = f(fa.value)
        def pure[A](fa: A): Bag[A] = Bag(fa)
      }
  }
  val bag = new Bag(1)
  val b = Monad[Bag]
  def f = (a:Int)=> a+1

  println(b.flatMap(bag)(n => Bag(n + 1)))
}
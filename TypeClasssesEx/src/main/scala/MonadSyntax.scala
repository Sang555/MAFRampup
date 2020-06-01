import cats.instances.option._
import cats.instances.list._
import cats.syntax.applicative._
import cats.syntax.functor._
import cats.syntax.flatMap._
import cats.Monad
import cats.Id


object MonadsSyntaxEx extends App {
  //demostration of monoids like Option, List, FlatMap, Identity Monad(transorms normalmvlaues to obey monad laws)

  def f1[F[_]: Monad](a:F[Int], b:F[Int]): F[Int] =
    a.flatMap(x => b.map(y => x*x + y*y))
  //Pure
  //Option
  println(1.pure[Option])
  //List
  println(1.pure[List])

  //FlatMap
  //Option
  println(f1(Option(1),Option(2)))
  println(f1(3 :Id[Int],4 : Id[Int]))

}
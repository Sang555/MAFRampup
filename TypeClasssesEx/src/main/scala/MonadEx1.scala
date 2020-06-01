
import cats.Monad
import cats.instances.list._
import cats.instances.option._
import cats.instances.vector._



object MonadEx extends App {
  //pure
  val opt1 = Monad[Option].pure(3)
  println(opt1)

  //using flatMap
  val opt2 = Monad[Option].flatMap(opt1)(a => Some(a + 1))
  println(opt2)

  //using list
  val l1 = Monad[List].flatMap(List(1, 2, 3, 4, 5))(a => List(a, a * 10))
  println(l1)


  //using vector
  val v1 = Monad[Vector].flatMap(Vector(1, 2, 3))(a => Vector(a, a * 10))
  println(v1)

}
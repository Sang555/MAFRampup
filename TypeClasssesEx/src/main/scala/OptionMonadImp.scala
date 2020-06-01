trait Monad[F[_]] {
  def pure[A](a:A) : F[A]
  def flatMap[A,B](fa: F[A])(f: A => F[B]): F[B]
}

object Monad{
  def apply[F[_]](implicit  M: Monad[F]): Monad[F] = M
  implicit val optionMonad = new Monad[MyOption] {
    def pure[A](a: A) = MySome(a)
    def flatMap[A, B](ma: MyOption[A])(f: A => MyOption[B]): MyOption[B] = ma match {
      case MyNone => MyNone
      case MySome(a) => f(a)
    }
  }
}

sealed trait MyOption[+A] {
  def flatMap[B](f: A => MyOption[B]): MyOption[B] =
    Monad[MyOption].flatMap(this)(f)
}

case object MyNone extends MyOption[Nothing]
case class MySome[A](x: A) extends MyOption[A]

import cats.syntax.either._

object LoginEx extends App {

  sealed trait LoginError extends Product with Serializable

  final case class NoUser(user: String) extends LoginError

  final case class IncorrectPassword(user: String) extends LoginError

  case class User(name: String, pwd: String)

  type LoginResult = Either[LoginError, User]

  def errorHandling(err: LoginError) =
    err match {
      case NoUser(u) => println("user not ofund")
      case IncorrectPassword(u) => println("incorrect pwd")
    }


  val result1: LoginResult = User("dave", "passw0rd").asRight
  println(result1)
}
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global
case class Address(street: String, city: String)
case class User(id: Int)
import scala.concurrent.duration._
import cats.data.OptionT
import cats.data._
import cats.implicits._


//cats has many monadTransformers like OptionT, EitherT
object MonadTransformerEx extends App {

  def findUserById(l: Long) = Future {
    l match {
      case 1L => Some(User(1))
      case default => None
    }
  }

//  def findAddressByUser(userId: Option[User]): Future[Option[Address]] = Future {
//    Some(Address("xx", "Bangalore"))
//  }

  def findAddressByUser2(userId: User): Future[Option[Address]] = Future {
    Some(Address("xx", "Bangalore"))
  }

  //this shows the error case with flatMap <- does the flatMap function but if we flatMap over a Future[Option[Address]] we get a Option[User
  //the monads do not compose directly
  //flatMap over A{x] and B[x] cannot generate flatMap A[B[x]]


//  def findAddressByUserId(id: Long): Future[Option[Address]] =
//    for {
//      user <- findUserById(id)
//      //need A=>M[B]
//      //if we flatMap over a Future{Option[User]] we get a Option[User]
//      //we need like 2 flatMap to get user...but this composition doesnthappens on own in scala, need to define own for A[B[x]]
//      address <- findAddressByUser2(user)
//    } yield address

  //like this we can have many wrappers
  //This is Option composed with Future
  case class FutOpt[A](value: Future[Option[A]]) {
    def map[B](f: A => B): FutOpt[B] =
      FutOpt(value.map(optA => optA.map(f)))

    def flatMap[B](f: A => FutOpt[B]): FutOpt[B] =
      FutOpt(value.flatMap(opt => opt match {
        case Some(a) => f(a).value
        case None => Future.successful(None)
      }))
  }

  def findAddressByUserId2(id: Long): Future[Option[Address]] =
    (for {
      user <- FutOpt(findUserById(id))
      //need A=>M[B]
      address <- FutOpt(findAddressByUser2(user))
    } yield address).value

  //this can be written as OptionT[Future, Address]
  def findAddressByUserId3(id: Long): Future[Option[Address]] =
    (for {
      user <- OptionT(findUserById(id))
      //need A=>M[B]
      address <- OptionT(findAddressByUser2(user))
    } yield address).value
  //println(Await.result(findAddressByUserId(1), 5.second))
  println(Await.result(findAddressByUserId2(1), 5.second))
}
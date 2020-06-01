
import cats.effect.IO
import io.circe.Json
import org.http4s.{HttpRoutes, HttpService}
import io.circe.generic.auto._
import org.http4s.circe.CirceEntityCodec._
import org.http4s.dsl.Http4sDsl


object AccountRoutes{

  private def errorBody(message: String) = Json.obj(
    ("message", Json.fromString(message))
  )


  def routes(accountService: AccountDAO[Account, Long, Balance]): HttpRoutes[IO] = {
    val dsl = new Http4sDsl[IO] {}
    import dsl._
    // IO(Response(status=200, headers=Headers(Content-Length: 0)))
    HttpRoutes.of[IO] {

      case req@POST -> Root / "account" =>
        req.decode[Account] { account =>
          accountService.open(account.no,account.name).
          flatMap {
                       case Left(message) => NotFound(errorBody(message))
                        case Right(_) => Ok()
                      }
        }

      case _@GET -> Root / "account" / no =>
        accountService.getMethod(no) flatMap {
          case Left(ex) => NotFound("account doesn't exist")
          case Right(acc) => Ok(acc)
        }
      case req@PUT -> Root / "account" /"credit" / no=>
        req.decode[Balance] { balance =>
          accountService.credit(no,balance.amount).
            flatMap {
              case Left(message) => NotFound(errorBody(message))
              case Right(_) => Ok()
            }
        }
      case req@PUT -> Root / "account"/"debit" / no=>
        req.decode[Balance] { balance =>
          accountService.debit(no,balance.amount).
            flatMap {
              case Left(message) => NotFound(errorBody(message))
              case Right(_) => Ok()
            }
        }

      //
      //      case req @ PUT -> Root / "books" / id =>
      //        req.decode[Book] { book =>
      //          bookDao.updateBook(BookId(id), book) flatMap {
      //            case Left(message) => NotFound(errorBody(message))
      //            case Right(_) => Ok()
      //          }
      //        }
    }

  }

}
//import cats.effect.IO
//import io.circe.Json
//import org.http4s.{HttpRoutes, HttpService}
//import io.circe.generic.auto._
//import org.http4s.circe.CirceEntityCodec._
//import org.http4s.dsl.Http4sDsl
//
//
//object BookRoutes{
//
//  private def errorBody(message: String) = Json.obj(
//    ("message", Json.fromString(message))
//  )
//
//
//  def routes(bookDao: BookDao): HttpRoutes[IO] = {
//    val dsl = new Http4sDsl[IO] {}
//    import dsl._
//    // IO(Response(status=200, headers=Headers(Content-Length: 0)))
//    HttpRoutes.of[IO] {
//      case _@GET -> Root / "books" =>
//        bookDao.getBooks().flatMap(books => Ok(books))
//
//      case req@POST -> Root / "books" =>
//        req.decode[Book] { book =>
//          bookDao.addBook(book).flatMap(id =>
//            Created(Json.obj(("id", Json.fromString(id.value))))
//          )
//        }
//
//      case _@GET -> Root / "books" / id =>
//        bookDao.getBook(BookId(id)) flatMap {
//          case None => NotFound()
//          case Some(book) => Ok(book)
//        }
//
//      case req @ PUT -> Root / "books" / id =>
//        req.decode[Book] { book =>
//          bookDao.updateBook(BookId(id), book) flatMap {
//            case Left(message) => NotFound(errorBody(message))
//            case Right(_) => Ok()
//          }
//        }
//    }
//
//  }
//
//}